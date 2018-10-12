package cn.peng.studygodpath.design.build.factorymethod.factory;


import cn.peng.studygodpath.design.build.factorymethod.MailSender;
import cn.peng.studygodpath.design.build.factorymethod.Sender;
import cn.peng.studygodpath.design.build.factorymethod.SenderEnum;
import cn.peng.studygodpath.design.build.factorymethod.SmsSender;

/**
 * Created by remote on 2017/7/10.
 */
public class GeneralFactory {



    public Sender create(SenderEnum type){
        Sender sender = null;
        switch (type){
            case MAIL:
                sender = new MailSender();
                break;
            case SMS:
                sender = new SmsSender();
                break;
            default:
                break;
        }
        return sender;
    }


}
