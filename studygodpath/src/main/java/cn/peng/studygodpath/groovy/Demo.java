package cn.peng.studygodpath.groovy;

import groovy.lang.GroovyClassLoader;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by remote on 2017/11/13.
 */
public class Demo {

    public static void main(String[] args)throws Exception {

        GroovyClassLoader loader = new GroovyClassLoader();
        Class aClass = loader.parseClass(new File(ResourceUtils.getURL("classpath:testFile").getFile()));
        List<Field> fields = getDeclaredAllFields(aClass);
        for (int i = 0;i < fields.size(); i++){
            System.out.println(fields.get(i).getName());
        }


    }

    public static List<Field> getDeclaredAllFields(Class clazz){
        //asList 返回的 ArraysList in Arrays add remove 等操作不支持。需要new ArrayList(list);
        List<Field> list = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        if(clazz !=  Object.class){
            list.addAll(getDeclaredAllFields(clazz.getSuperclass()));
        }
        return  list;
    }




}
