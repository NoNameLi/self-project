package cn.peng.studygodpath.design.behavior.status.Demo.state;

import java.util.Map;

import com.google.common.collect.Maps;

import cn.peng.studygodpath.design.behavior.status.Demo.Order;
import cn.peng.studygodpath.design.behavior.status.Demo.OrderStateEnum;

/**
 * 方法应该对应着具体的状态枚举
 * 
 * @author remote
 *
 */
public class StatusChangeServiceImpl implements StatusChangeService {

    private Map<OrderStateEnum, OrderStateHandler> handlers = null;

    public StatusChangeServiceImpl() {
        this.init();
    }

    // @PostConstruct 在spring bean创建后会立刻调用 相当于 xml bean上的 init-method
    public void init() {
        handlers = Maps.newHashMap();
        handlers.put(OrderStateEnum.init, new OrderedStateHandler());
        handlers.put(OrderStateEnum.confirmed, new ConfirmedStateHandler());
        handlers.put(OrderStateEnum.payed, new PayedStateHandler());
    }

    @Override
    public void confirm(Order order) {
        handlers.get(order.getOrderState()).confirm(order);
    }

    @Override
    public void modify(Order order) {
        handlers.get(order.getOrderState()).modify(order);
    }

    @Override
    public void pay(Order order) {
        handlers.get(order.getOrderState()).pay(order);
    }

    @Override
    public void change(Order order, OrderStateEnum newStatus) {
        // 可以根据 当前状态和新状态 确定获取 变化执行的handler 没有的找不到的，则不能变更
    }

}
