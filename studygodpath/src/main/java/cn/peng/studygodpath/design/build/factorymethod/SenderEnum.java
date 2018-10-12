package cn.peng.studygodpath.design.build.factorymethod;

/**
 * Created by remote on 2017/7/10.
 */
public enum SenderEnum {

    MAIL("mail"),
    SMS("sms");

    @SuppressWarnings("unused")
    private String type;
    private SenderEnum(String type){
        this.type = type;
    }
}
