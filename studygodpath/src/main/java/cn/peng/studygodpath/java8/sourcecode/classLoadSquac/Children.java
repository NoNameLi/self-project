package cn.peng.studygodpath.java8.sourcecode.classLoadSquac;

/**
 * Created by remote on 2017/9/28.
 */
public class Children extends Partner2 {

    static {
        System.out.println("child static code");
    }

    public Children() {
        System.out.println("create children class");
    }

    public static void main(String[] args) {
        new Children();
//        System.out.println(Children.name);
    }

}
