package cn.peng.studygodpath.design.behavior.status.Demo.state;

import cn.peng.studygodpath.design.behavior.status.Demo.Order;
import cn.peng.studygodpath.design.behavior.status.Demo.OrderStateEnum;

/**
 * Created by remote on 2018/3/2.
 */
public class PayedStateHandler extends OrderStateHandler {
    public PayedStateHandler() {
        value = OrderStateEnum.payed;
    }

    @Override
    public void confirm(Order order) {
        System.out.println("工单付款，不能再次确认");
    }

    @Override
    public void modify(Order order) {
        System.out.println("工单付款，不能修改");
    }

    @Override
    public void pay(Order order) {
        System.out.println("工单付款，不能再次付款");
    }
}
