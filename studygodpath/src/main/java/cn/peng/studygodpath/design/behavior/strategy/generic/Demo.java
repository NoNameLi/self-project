package cn.peng.studygodpath.design.behavior.strategy.generic;

import cn.hutool.extra.spring.SpringUtil;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@ComponentScan("cn.peng.studygodpath.design.behavior.strategy.generic")
public class Demo {

    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Demo.class);
        GenericFactory<String, StrategyAConfig.StrategyA> strategyFactoryA = (GenericFactory<String, StrategyAConfig.StrategyA>) context.getBean("strategyFactoryA");
        GenericFactory<StrategyBConfig.StrategyBEnum, StrategyBConfig.StrategyB> strategyFactoryB = (GenericFactory<StrategyBConfig.StrategyBEnum, StrategyBConfig.StrategyB>) context.getBean("strategyFactoryB");


        strategyFactoryA.getStrategy("a").handle();
        strategyFactoryA.getStrategy("b").handle();
        Assert.assertEquals(strategyFactoryB.getStrategy(StrategyBConfig.StrategyBEnum.C).handle(null), "c");
        Assert.assertEquals(strategyFactoryB.getStrategy(StrategyBConfig.StrategyBEnum.D).handle(null), "d");
    }


}

