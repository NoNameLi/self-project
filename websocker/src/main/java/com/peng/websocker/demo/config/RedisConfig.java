package com.peng.websocker.demo.config;

import com.peng.websocker.demo.handler.RedisReceiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-19 15:28
 * QDescription:
 */

@Configuration
public class RedisConfig {

    @Value("${redis.channel.msgToAll}")
    private String msgToAll;

    @Value("${redis.channel.noticeToAll}")
    private String noticeToAll;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory
            , MessageListenerAdapter messageListenerAdapter1, MessageListenerAdapter messageListenerAdapter2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter1, new PatternTopic(msgToAll));
        container.addMessageListener(messageListenerAdapter2, new PatternTopic(noticeToAll));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter1(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "msgToAllHandle");
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter2(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "noticeToAllHandle");
    }
}
