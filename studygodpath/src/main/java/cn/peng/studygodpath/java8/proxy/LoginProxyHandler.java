package cn.peng.studygodpath.java8.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by remote on 2018/1/5.
 */
public class LoginProxyHandler implements InvocationHandler {

    Object trage;

    public Object bing(Object trage) {
        this.trage = trage;
        return Proxy.newProxyInstance(trage.getClass().getClassLoader(), trage.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("校验参数");
        Object obj = null;
        try{
            obj = method.invoke(trage, args);
            System.out.println("执行结果：" + obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
