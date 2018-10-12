package cn.peng.studygodpath.spring4.redis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("cn.peng.studygodpath.spring4.redis")
@Import(RedisConfiguration.class)
public class ApplicationConfigurable {




}
