package cn.peng.studygodpath.design.build.singleton;

import java.io.Serializable;

/**
 * Created by remote on 2018/1/17.
 * 懒汉时 懒加载
 *
 * 多线程下不安全
 *
 */
public class Singleton1 implements Serializable {
    private static final long serialVersionUID = -1733852986207899857L;

    private Singleton1(){}

    private static Singleton1 install = null;

    public static Singleton1 getInstall(){
        if(null == install){
            install = new Singleton1();
        }
        return install;
    }
}
