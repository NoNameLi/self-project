package cn.peng.studygodpath.design.build.singleton;

/**
 * Created by remote on 2018/1/17.
 * 懒汉式 懒加载 多线程同步 性能影响十分严重
 */
public class Singleton2 {

    private  Singleton2(){}
    private static Singleton2 install = null;

    public static synchronized  Singleton2 getInstall(){
        if(null == install){install = new Singleton2();}
        return  install;
    }
}
