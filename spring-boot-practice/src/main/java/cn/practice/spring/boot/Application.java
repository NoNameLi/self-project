package cn.practice.spring.boot;

import cn.practice.spring.boot.model.Message;
import cn.practice.spring.boot.repository.MessageRepository;
import cn.practice.spring.boot.repository.impl.InMemoryMessageRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 11:08
 * QDescription:
 */
@SpringBootApplication
public class Application {

    @Bean
    public MessageRepository messageRepository() {
        return new InMemoryMessageRepository();
    }

    @Bean// 用于 cn.practice.spring.boot.controller.view.MessageController.view
    public Converter<String, Message> messageConverter() {
        return new Converter<String, Message>() {
            @Override
            public Message convert(String id) {
                return messageRepository().get(Long.valueOf(id));
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
