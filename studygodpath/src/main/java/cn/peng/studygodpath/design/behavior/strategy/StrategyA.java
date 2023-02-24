package cn.peng.studygodpath.design.behavior.strategy;

public class StrategyA implements RewardStrategy {

    @Override
    public Double computeReward(Object data) {

        return 100D;
    }

}
