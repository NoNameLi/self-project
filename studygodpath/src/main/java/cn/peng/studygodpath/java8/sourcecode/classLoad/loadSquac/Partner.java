package cn.peng.studygodpath.java8.sourcecode.classLoad.loadSquac;

/**
 * Created by remote on 2018/4/14.
 */
public class Partner {

    public static String sv = prtString("partner 的静态变量");

    public static Partner partner = new Partner();

    public static final int MAX = 100;

    static {
        System.out.println("parnter 的静态代码块");
    }

    private String v = prtString("partner 的成员变量");

    {
        System.out.println("parnter 的代码块...");
    }

    public static String prtString(String obj) {
        System.out.println(obj);
        return null;
    }

    public Partner() {
        System.out.println("partner 的构造函数");
    }


    public static void main(String[] args) {
        Partner partner = new Partner();
    }
}
