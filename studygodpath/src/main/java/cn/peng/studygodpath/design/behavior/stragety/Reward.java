package cn.peng.studygodpath.design.behavior.stragety;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reward {

    /** 计算奖励的依据数据 */
    private Object data;

    private RewardStragety stragety;

    public Double compute() {
        return stragety.computeReward(data);
    }

    public static void main(String[] args) {
        Reward reward = new Reward("test", new StragetyA());
        System.out.println(reward.compute());
        reward = new Reward("test", new StragetyB());
        System.out.println(reward.compute());

        reward = new Reward("test", new StragetyC());
        System.out.println(reward.compute());
    }

}
