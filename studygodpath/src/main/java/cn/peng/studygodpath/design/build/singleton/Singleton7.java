package cn.peng.studygodpath.design.build.singleton;

import java.io.Serializable;

/**
 * Created by remote on 2017/7/11.
 */
public final class Singleton7 implements Serializable {
    private static final long serialVersionUID = -8776481248554447375L;
    private static Singleton7 instance = null;

    private Singleton7(){}

    public static Singleton7 getInstance(){
        if(null == instance){
            synchronized (instance){
                if(null == instance)
                    instance = new Singleton7();
            }
        }
        return instance;
    }
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
    * 会被ObjectOutputStream , ObjectInputStream 调用
    * */
    public Object readResolve(){
        return instance;
    }
}
