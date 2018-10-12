package cn.peng.studygodpath.design.build.singleton;

/**
 * Created by remote on 2018/1/17.
 * 饿汉式的变种
 */
public class Singleton4 {

    private Singleton4(){}

    private static Singleton4 install = null;
    static {
        install = new Singleton4();
    }
    public static Singleton4 getInstall(){
        return install;
    }
}
