package cn.peng.studygodpath.design.build.factorymethod;

/**
 * Created by remote on 2017/7/10.
 */
public interface Sender {
    boolean send();

    SenderEnum getSenderName();
}
