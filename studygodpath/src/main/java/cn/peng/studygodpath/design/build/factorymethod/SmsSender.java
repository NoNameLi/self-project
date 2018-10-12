package cn.peng.studygodpath.design.build.factorymethod;

/**
 * Created by remote on 2017/7/10.
 */
public class SmsSender implements Sender{
    private static final SenderEnum senderName = SenderEnum.SMS;

    public boolean send() {
        return true;
    }

    public SenderEnum getSenderName() {
        return senderName;
    }
}
