package cn.peng.chat.biz;

import org.springframework.stereotype.Service;


@Service
public class TestBean implements TestInterface{

    @Override
    public boolean testMethod() {
        return false;
    }
}
