package cn.peng.studygodpath.design.behavior.strategy;

public class StrategyB implements RewardStrategy {

    @Override
    public Double computeReward(Object data) {
        return 300D;
    }

}
