package cn.practice.spring.boot.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 15:02
 * QDescription:
 */
@Component
@Order(2)
public class CommandTwo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("commandTwo run ....");
    }
}
