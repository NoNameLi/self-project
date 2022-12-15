package cn.peng.studygodpath.design.build.singleton;

import java.io.Serializable;

/**
 * Created by remote on 2017/7/11.
 *
 * 这种方式 的单例也是有问题的
 *  因为 instance = new Sigleton7() 不是一个原子操作
 *  有 3个步骤
 *      1. 分配内存
 *      2. 初始化对象
 *      3. 将对象地址赋值给应用
 *   而 2. 3在jvm中有可能重排 导致执行顺序 为 1 .3 2，导致获取到的是为进行初始化的对象
 *
 *   解决方案： 在 instance 加入 volatile
 *
 */
public final class Singleton7 implements Serializable {
    private static final long serialVersionUID = -8776481248554447375L;
    private static volatile Singleton7 instance = null;

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
