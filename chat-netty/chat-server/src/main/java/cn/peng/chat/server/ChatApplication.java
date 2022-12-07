package cn.peng.chat.server;

import cn.peng.chat.server.config.SpringConfiguration;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChatApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ChatServer chatServer = applicationContext.getBean(ChatServer.class);
//        new ClassPathXmlApplicationContext("classpath:applicationfile.xml");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
//        beanFactory.registerSingleton();
        chatServer.start();
    }
}
