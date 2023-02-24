package cn.peng.studygodpath.design.behavior.strategy.generic;


import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericFactory<T, Z extends GenericStrategy<T>> implements InitializingBean {

    protected Map<T, Z> strategyMap = null;

    protected Class<Z> strategyClass;

    public GenericFactory(Class<Z> strategyClass) {
        this.strategyClass = strategyClass;
    }

    public Z getStrategy(T type) {
        Objects.requireNonNull(this.strategyMap, "工厂未初始化成功");
        return strategyMap.get(type);
    }

    @Override
    public void afterPropertiesSet() {
        ApplicationContext context = SpringUtil.getApplicationContext();
        strategyMap = context.getBeansOfType(strategyClass).values().stream().collect(Collectors.toMap(GenericStrategy::getType, Function.identity()));
    }

}
