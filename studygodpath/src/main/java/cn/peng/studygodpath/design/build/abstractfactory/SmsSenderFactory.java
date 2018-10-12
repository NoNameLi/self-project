package cn.peng.studygodpath.design.build.abstractfactory;


import cn.peng.studygodpath.design.build.factorymethod.Sender;
import cn.peng.studygodpath.design.build.factorymethod.SmsSender;

/**
 * Created by remote on 2017/7/11.
 */
public class SmsSenderFactory implements AbstractSenderFactory{

    public Sender create() {
        return new SmsSender();
    }
}
