package cn.peng.studygodpath.groovy;

import cn.peng.studygodpath.FilePathUtil;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

/**
 * Created by remote on 2017/11/15.
 */
public class JavaUseGroovyTest {
    static Object runWithGroovyClassLoader() throws Exception {
        // 获取当前资源路径，用于指定Groovy脚本
        String base_path = FilePathUtil.getProjectPath();
        // 使用当前线程的context创建GroovyClassLoader
        // parseClass()方法将文件解析成可以运行的class
        Class aClass = new GroovyClassLoader().parseClass(new File(base_path + "Test.groovy"));
        // 创建此 Class 对象所表示的类的一个新实例
        GroovyObject groovyObject = (GroovyObject) aClass.newInstance();
        // groovy 方法的入参，多个参数从左到右书写，无入参保持为空new Object[]{}
        Object[] objects = new Object[]{1, 3, 2};
        if(groovyObject instanceof AbstractClass){
            AbstractClass install = (AbstractClass)groovyObject;
            install.exect();
        }
        // 调用方法 testC 并获得返回值(如果后者存在)
        return groovyObject.invokeMethod("testC", objects);
    }


    public static void main(String[] args) throws Exception {
        // 将返回值打印到控制台
        System.out.println(runWithGroovyClassLoader());
    }

}
