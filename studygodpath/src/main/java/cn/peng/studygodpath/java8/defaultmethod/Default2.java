package cn.peng.studygodpath.java8.defaultmethod;

/**
 * Created by remote on 2018/1/20.
 */
public interface Default2 {

    default  void two(){
        System.out.println(Default2.class.getSimpleName());
    }

    static void interfaceStatic(){
        System.out.println("this is interface static method");
    }
}
