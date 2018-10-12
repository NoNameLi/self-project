package cn.peng.studygodpath.java8.overLoad;

/**
 * Created by remote on 2018/1/9.
 */
public class OverLoadMethod {


    public void test(int i){
        System.out.printf(String.valueOf(i));
    }

    public void test(String str){
        System.out.println(str);
    }

    public void test(Boolean bool){
        System.out.println(bool);
    }
}
