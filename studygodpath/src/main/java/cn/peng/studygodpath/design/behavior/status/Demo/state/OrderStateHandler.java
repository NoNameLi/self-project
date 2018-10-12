package cn.peng.studygodpath.design.behavior.status.Demo.state;

import cn.peng.studygodpath.design.behavior.status.Demo.Order;
import cn.peng.studygodpath.design.behavior.status.Demo.OrderStateEnum;

/**
 * Created by remote on 2018/3/2.
 * 三个状态：已预订、已确认、已支付
 * 三个行为：确认、修改、支付

 */
public abstract class OrderStateHandler {
    public OrderStateEnum value;
    public abstract void confirm(Order order);
    public abstract void modify(Order order);
    public abstract void pay(Order order);
}
