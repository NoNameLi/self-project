package cn.peng.studygodpath.java8.sourcecode.classLoadSquac;

/**
 * Created by remote on 2017/9/28.
 */
public class Partner2 {

    public static final String name = "partner";

    static {
        System.out.println("static value is " + name);
        System.out.println("partner static code");
    }

    public Partner2() {
        System.out.println("create partner class");
    }

}
