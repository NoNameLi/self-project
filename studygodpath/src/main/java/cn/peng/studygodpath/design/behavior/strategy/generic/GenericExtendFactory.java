package cn.peng.studygodpath.design.behavior.strategy.generic;


import java.util.Objects;

public class GenericExtendFactory<T, P, E, Z extends GenericExtendStrategy<T, P, E>> extends GenericFactory<T, Z> {

    public GenericExtendFactory(Class<Z> strategyClass) {
        super(strategyClass);
    }

    public E handle(T type, P param) {
        GenericStrategy<T> strategy = this.getStrategy(type);
        Objects.requireNonNull(strategy, "不支持策略类型：" + type.toString());
        return ((GenericExtendStrategy<T, P, E>) strategy).handle(param);
    }

}
