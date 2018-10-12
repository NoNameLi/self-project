package cn.peng.studygodpath.design.build.abstractfactory;


import cn.peng.studygodpath.design.build.factorymethod.MailSender;
import cn.peng.studygodpath.design.build.factorymethod.Sender;

/**
 * Created by remote on 2017/7/11.
 */
public class MailSenderFactory implements AbstractSenderFactory {

    public Sender create() {
        return new MailSender();
    }
}
