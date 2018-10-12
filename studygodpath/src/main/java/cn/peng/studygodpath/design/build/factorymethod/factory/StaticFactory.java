package cn.peng.studygodpath.design.build.factorymethod.factory;


import cn.peng.studygodpath.design.build.factorymethod.MailSender;
import cn.peng.studygodpath.design.build.factorymethod.Sender;
import cn.peng.studygodpath.design.build.factorymethod.SmsSender;

/**
 * Created by remote on 2017/7/10.
 */
public class StaticFactory {

    public static Sender createMail(){
        return new MailSender();
    }

    public static Sender createSms(){
        return new SmsSender();
    }
}
