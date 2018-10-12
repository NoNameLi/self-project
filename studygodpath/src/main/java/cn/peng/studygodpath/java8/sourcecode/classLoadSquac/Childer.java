package cn.peng.studygodpath.java8.sourcecode.classLoadSquac;

/**
 * Created by remote on 2018/4/14.
 */
public class Childer extends  Partner{
    static{
        System.out.println("chiider 的static代码块...");
    }
    public static String ss2 = prtString("chiider 的static变量...");

    private static Childer a = new Childer();

    {
        System.out.println("chiider 代码块...");
    }

    public String ss1 = prtString("B的成员变量...");

    public Childer(){
        System.out.println("chiider 的构造函数...");
    }

    public static void main(String[] args) {
        Class<Childer> aClass = Childer.class;
    }
}
