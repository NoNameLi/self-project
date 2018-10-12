package cn.peng.studygodpath.design.behavior.status.Demo.state;

import cn.peng.studygodpath.design.behavior.status.Demo.Order;
import cn.peng.studygodpath.design.behavior.status.Demo.OrderStateEnum;

/**
 * Created by remote on 2018/3/2.
 */
public class ConfirmedStateHandler extends OrderStateHandler {

    public ConfirmedStateHandler() {
        value = OrderStateEnum.confirmed;
    }

    @Override
    public void confirm(Order order) {
        System.out.println("工单已确认，不能再次确认");
    }

    @Override
    public void modify(Order order) {
        System.out.println("工单已确认，不能修改");
    }

    @Override
    public void pay(Order order) {
        System.out.println("工单支付成功");
        order.setOrderState(OrderStateEnum.payed);
    }
}
