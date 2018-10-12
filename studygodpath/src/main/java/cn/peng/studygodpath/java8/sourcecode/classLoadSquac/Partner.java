package cn.peng.studygodpath.java8.sourcecode.classLoadSquac;

/**
 * Created by remote on 2018/4/14.
 */
public class Partner {

    private static String sv = prtString("partner 的静态变量");
    static{
        System.out.println("parnter 的静态代码块");
    }
    private String v = prtString("partner 的成员变量变量");

    {
        System.out.println("parnter 的代码块...");
    }

    public static String prtString(String obj){
        System.out.println(obj);
        return null;
    }

    public Partner(){
        System.out.println("partner 的构造函数");
    }

}
