package cn.peng.studygodpath.java8.lambda;

import java.util.*;

/**
 * Created by remote on 2017/7/7.
 */
public class First {

    public static void main(String[] args){
        List<String> list = Arrays.asList("abc","aac","aab","aaa");
        Collections.sort(list, Comparator.naturalOrder());
        System.out.println(list.toString());

        List<String> list2 = Arrays.asList("abc","aac","aab","aaa");
        Collections.sort(list2, Comparator.naturalOrder());
        System.out.println(list2.toString());

        List<String> list3 = Arrays.asList("abc","aac","aab","aaa");
        list3.sort(String::compareTo);
        System.out.println(list3.toString());
    }

}
