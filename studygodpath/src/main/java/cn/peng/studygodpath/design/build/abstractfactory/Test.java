package cn.peng.studygodpath.design.build.abstractfactory;


import cn.peng.studygodpath.design.build.factorymethod.SenderInstance;

/**
 * Created by remote on 2017/7/11.
 */
public class Test {

    @org.testng.annotations.Test
    public void test(){
        SmsSenderFactory smsSenderFactory = new SmsSenderFactory();
        SenderInstance instance = new SenderInstance(smsSenderFactory.create());
        instance.send();
    }
}
