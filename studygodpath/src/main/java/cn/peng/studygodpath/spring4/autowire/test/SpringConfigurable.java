package cn.peng.studygodpath.spring4.autowire.test;

/**
 * 一个接口多个实现时，自动注入式靠BeanName 为依据
 * 一个接口一个实现时，可以为name 也可以为class
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedInputStream;

/**
 * Created by remote on 2017/9/25.
 */
//@Configurable 不要两个注解搞混
@Configuration
@ComponentScan("cn.peng.studygodpath.spring4.autowire")
public class SpringConfigurable {


}
