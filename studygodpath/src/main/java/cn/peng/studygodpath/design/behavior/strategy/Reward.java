package cn.peng.studygodpath.design.behavior.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reward {

    /** 计算奖励的依据数据 */
    private Object data;

    private RewardStrategy stragety;

    public Double compute() {
        return stragety.computeReward(data);
    }

    public static void main(String[] args) {
        Reward reward = new Reward("test", new StrategyA());
        System.out.println(reward.compute());
        reward = new Reward("test", new StrategyB());
        System.out.println(reward.compute());

        reward = new Reward("test", new StrategyC());
        System.out.println(reward.compute());
    }

}
