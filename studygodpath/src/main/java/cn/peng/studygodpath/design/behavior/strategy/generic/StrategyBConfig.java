package cn.peng.studygodpath.design.behavior.strategy.generic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class StrategyBConfig {
    interface StrategyB extends GenericStrategy<StrategyBEnum> {
        String handle(Map<String, Object> param);
    }

    enum StrategyBEnum {
        C, D
    }

    @Bean
    public GenericFactory<StrategyBEnum, StrategyB> strategyFactoryB() {
        return new GenericFactory<>(StrategyB.class);
    }

    @Bean
    public StrategyB strategyB1() {
        return new StrategyB() {

            @Override
            public StrategyBEnum getType() {
                return StrategyBEnum.C;
            }

            @Override
            public String handle(Map<String, Object> param) {
                System.out.println("执行c策略");
                return "c";
            }
        };
    }

    @Bean
    public StrategyB strategyB2() {
        return new StrategyB() {

            @Override
            public StrategyBEnum getType() {
                return StrategyBEnum.D;
            }

            @Override
            public String handle(Map<String, Object> param) {
                System.out.println("执行d策略");
                return "d";
            }
        };
    }
}
