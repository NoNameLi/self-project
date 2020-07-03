package cn.peng.studygodpath.spring4.aop;

import cn.peng.studygodpath.spring4.redis.RedisConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("cn.peng.studygodpath.spring4.aop")
@Import(RedisConfiguration.class)
public class ApplicationConfigurable {




}
