package cn.peng.studygodpath.design.behavior.status.Demo;

/**
 * Created by remote on 2018/3/2.
 */
@SuppressWarnings("unused")
public enum OrderStateEnum {

    init("已预订",0),
    confirmed("已确认",1),
    payed("已支付",1),

    ;

    private String name;
    private int value;

    private OrderStateEnum(String name,int value){
        this.name = name;
        this.value = value;
    }
}

