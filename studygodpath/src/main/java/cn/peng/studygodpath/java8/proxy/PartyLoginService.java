package cn.peng.studygodpath.java8.proxy;

/**
 * Created by remote on 2018/1/5.
 */
public interface PartyLoginService {


    String loginSystem();

    String isLoginSuccess(String cookie);

    default String getCookie(String account,String password){
        //logic
        return null;
    }
}
