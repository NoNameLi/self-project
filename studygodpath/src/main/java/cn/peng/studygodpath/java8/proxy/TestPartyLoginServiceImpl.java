package cn.peng.studygodpath.java8.proxy;

/**
 * Created by remote on 2018/1/5.
 */
public class TestPartyLoginServiceImpl implements PartyLoginService{
    @Override
    public String loginSystem() {
        System.out.println("登录成功");
        return "Cookie:xsdfadfaf";
    }

    @Override
    public String isLoginSuccess(String cookie) {
        System.out.println("校验，登录成功");
        return null;
    }
}
