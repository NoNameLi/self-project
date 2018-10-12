package cn.peng.studygodpath.design.behavior.status.Demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by remote on 2018/3/2.
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Order {
    /** 其他变量 */
    private OrderStateEnum orderState;

}
