package cn.peng.nacos.example.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan("cn.peng.nacos.example.spring.")
public class AppConfig {
}
