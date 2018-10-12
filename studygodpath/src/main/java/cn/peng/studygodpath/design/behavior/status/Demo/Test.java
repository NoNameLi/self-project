package cn.peng.studygodpath.design.behavior.status.Demo;

import cn.peng.studygodpath.design.behavior.status.Demo.state.StatusChangeService;
import cn.peng.studygodpath.design.behavior.status.Demo.state.StatusChangeServiceImpl;

public class Test {

    public static void main(String[] args) {
        // 这总方式不符合开闭原则
        StatusChangeService statusChangeService = new StatusChangeServiceImpl();

        Order order = new Order(OrderStateEnum.init);
        statusChangeService.modify(order);
        System.out.println(order);
        statusChangeService.pay(order);
        System.out.println(order);
        statusChangeService.confirm(order);
        System.out.println(order);
        statusChangeService.pay(order);
        System.out.println(order);
    }
}
