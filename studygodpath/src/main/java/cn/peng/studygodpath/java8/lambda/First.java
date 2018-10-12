package cn.peng.studygodpath.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by remote on 2017/7/7.
 */
public class First {

    public static void main(String[] args){
        List<String> list = Arrays.asList("abc","aac","aab","aaa");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        System.out.println(list.toString());

        List<String> list2 = Arrays.asList("abc","aac","aab","aaa");
        Collections.sort(list2,(String a,String b)->{return a.compareTo(b);});
        System.out.println(list2.toString());

        List<String> list3 = Arrays.asList("abc","aac","aab","aaa");
        list3.sort((a,b)->a.compareTo(b));
        System.out.println(list3.toString());
    }

}
