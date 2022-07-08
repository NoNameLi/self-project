package cn.peng.chat.server;

import cn.peng.chat.server.config.SpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ChatApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ChatServer chatServer = applicationContext.getBean(ChatServer.class);
        chatServer.start();
    }
}
