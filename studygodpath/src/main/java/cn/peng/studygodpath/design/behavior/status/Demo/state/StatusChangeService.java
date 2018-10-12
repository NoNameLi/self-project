package cn.peng.studygodpath.design.behavior.status.Demo.state;

import cn.peng.studygodpath.design.behavior.status.Demo.Order;
import cn.peng.studygodpath.design.behavior.status.Demo.OrderStateEnum;

/**
 * 方法应该对应着具体的状态枚举
 * 
 * @author remote
 *
 */
public interface StatusChangeService {

    void confirm(Order order);

    void modify(Order order);

    void pay(Order order);

    void change(Order order,OrderStateEnum newStatus);

}
