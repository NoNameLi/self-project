package cn.peng.studygodpath.design.build.factorymethod.factory;


import cn.peng.studygodpath.design.build.factorymethod.MailSender;
import cn.peng.studygodpath.design.build.factorymethod.Sender;
import cn.peng.studygodpath.design.build.factorymethod.SmsSender;

/**
 * Created by remote on 2017/7/10.
 */
public class MultipleFactory {

    public Sender createMail(){
        return new MailSender();
    }

    public Sender createSms(){
        return new SmsSender();
    }
}
