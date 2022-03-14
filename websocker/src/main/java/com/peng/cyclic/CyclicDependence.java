package com.peng.cyclic;

import com.peng.cyclic.bean.Cyclic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: Administrator
 * @CreateTime:2022-03-10 17:02
 * QDescription:
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class CyclicDependence {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CyclicDependence.class, args);

        Cyclic bean = applicationContext.getBean(Cyclic.class);
        System.out.println(bean.getClass());
    }
}
