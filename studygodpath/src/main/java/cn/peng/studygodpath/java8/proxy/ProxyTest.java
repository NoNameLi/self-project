package cn.peng.studygodpath.java8.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by remote on 2018/1/5.
 * 动态代理还是研究一下框架源码 才能更好地理解如何使用
 */
public class ProxyTest {

    public static void main(String[] args) {

        PartyLoginService service = new TestPartyLoginServiceImpl();
        PartyLoginService proxyInstance = (PartyLoginService) new LoginProxyHandler().bing(service);
        proxyInstance.loginSystem();

//        UserService userService = new UserServiceImpl();
//        MyInvocationHandler invocationHandler = new MyInvocationHandler(
//                userService);
//
//        UserService proxy = (UserService) invocationHandler.getProxy();
//        proxy.add();

    }

}
