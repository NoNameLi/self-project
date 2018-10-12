package cn.peng.studygodpath.design.behavior.status.Demo.state;

import cn.peng.studygodpath.design.behavior.status.Demo.Order;
import cn.peng.studygodpath.design.behavior.status.Demo.OrderStateEnum;

/**
 * Created by remote on 2018/3/2.
 */
public class OrderedStateHandler extends OrderStateHandler {

    public OrderedStateHandler() {
        value = OrderStateEnum.init;
    }

    @Override
    public void confirm(Order order) {
        System.out.println("确认成功");
        order.setOrderState(OrderStateEnum.confirmed);
    }

    @Override
    public void modify(Order order) {
        System.out.println("修改成功");
        order.setOrderState(OrderStateEnum.init);
    }

    @Override
    public void pay(Order order) {
        System.out.println("预定状态下不能付款");

    }
}
