package cn.peng.studygodpath.design.behavior.strategy;

public interface RewardStrategy {

    /**
     * 计算激励
     * 
     * @param data
     */
    Double computeReward(Object data);
}
