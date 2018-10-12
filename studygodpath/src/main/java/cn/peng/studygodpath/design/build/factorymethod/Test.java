package cn.peng.studygodpath.design.build.factorymethod;


import cn.peng.studygodpath.design.build.factorymethod.factory.GeneralFactory;
import cn.peng.studygodpath.design.build.factorymethod.factory.MultipleFactory;
import cn.peng.studygodpath.design.build.factorymethod.factory.StaticFactory;

/**
 * Created by remote on 2017/7/10.
 */
public class Test {

    @org.testng.annotations.Test
    public void GeneralFactoryTest(){
        GeneralFactory factory = new GeneralFactory();
        SenderInstance instance = new SenderInstance(factory.create(SenderEnum.MAIL));
        instance.send();
        instance.setSender(factory.create(SenderEnum.SMS));
        instance.send();
    }

    @org.testng.annotations.Test
    public void MultipleFactory(){
        MultipleFactory factory = new MultipleFactory();

        SenderInstance instance = new SenderInstance(factory.createMail());
        instance.send();
        instance.setSender(factory.createSms());
        instance.send();
    }

    @org.testng.annotations.Test
    public void StaticFactory(){
        SenderInstance instance = new SenderInstance(StaticFactory.createSms());
        instance.send();
        instance.setSender(StaticFactory.createMail());
        instance.send();
    }
}
