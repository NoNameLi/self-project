package cn.practice.spring.boot.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 15:00
 * QDescription:
 */
@Component
@Order(1)
public class CommandOne implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("commandOne run....");
    }
}
