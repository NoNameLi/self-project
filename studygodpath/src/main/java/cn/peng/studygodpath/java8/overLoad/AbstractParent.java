package cn.peng.studygodpath.java8.overLoad;

/**
 * Created by remote on 2018/1/25.
 */
public class AbstractParent {

    public void hello(){
        println("hello");
    }
    private void println(Object obj){
        System.out.println(String.valueOf(obj));
    }
}
