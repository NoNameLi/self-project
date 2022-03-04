package com.peng.websocker.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//启用WebSocket服务器
public class WebSockerConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")// websocket端点，客户端将使用它连接到我们的websocket服务器。
                .withSockJS();//withSockJS()是用来为不支持websocket的浏览器启用后备选项，使用了SockJS。

    }

    //配置一个消息代理，用于将消息从一个客户端路由到另一个客户端
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //以“/app”开头的消息应该路由到使用@MessageMapping注释的消息处理方法
        registry.setApplicationDestinationPrefixes("/app");
        //以“/topic”开头的消息应该路由到消息代理  topic和user这两个域上可以向客户端发消息
        registry.enableSimpleBroker("/topic", "/user");
        //指定用户发送（一对一）的主题前缀是“/user/”
        registry.setUserDestinationPrefix("/user");
    }
}
