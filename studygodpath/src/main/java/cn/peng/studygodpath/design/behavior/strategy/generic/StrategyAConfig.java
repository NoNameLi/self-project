package cn.peng.studygodpath.design.behavior.strategy.generic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyAConfig {
    interface StrategyA extends GenericStrategy<String> {
        void handle();
    }

    @Bean
    public GenericFactory<String, StrategyA> strategyFactoryA() {
        return new GenericFactory<>(StrategyA.class);
    }

    @Bean
    public StrategyA strategyA2() {
        return new StrategyA() {
            @Override
            public String getType() {
                return "a";
            }

            @Override
            public void handle() {
                System.out.println("执行a策略");
            }
        };
    }

    @Bean
    public StrategyA strategyA() {
        return new StrategyA() {
            @Override
            public String getType() {
                return "b";
            }

            @Override
            public void handle() {
                System.out.println("执行b策略");
            }
        };
    }

}
