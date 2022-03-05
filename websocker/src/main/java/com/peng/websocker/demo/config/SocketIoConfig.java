package com.peng.websocker.demo.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.peng.websocker.demo.properties.SocketIoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Administrator
 * @CreateTime:2022-03-04 15:04
 * QDescription:
 */
@Configuration
@EnableConfigurationProperties(SocketIoProperties.class)
@RequiredArgsConstructor
public class SocketIoConfig {

    private final SocketIoProperties socketIoProperties;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration conf = new com.corundumstudio.socketio.Configuration();
        conf.setHostname(socketIoProperties.getHost());
        conf.setPort(socketIoProperties.getPort());
        conf.setAllowCustomRequests(socketIoProperties.isAllowCustomRequests());
        conf.setBossThreads(socketIoProperties.getBossThreads());
        conf.setWorkerThreads(socketIoProperties.getWorkerThreads());
        conf.setUpgradeTimeout(socketIoProperties.getUpgradeTimeout());
        conf.setPingTimeout(socketIoProperties.getPingTimeout());
        conf.setPingInterval(socketIoProperties.getPingInterval());

        conf.setAuthorizationListener(handshakeData -> {
            //http://localhost:8081?username=test&password=test
            //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息
            String token = handshakeData.getSingleUrlParam("token");
            return true;
        });

        SocketIOServer socketIOServer = new SocketIOServer(conf);
//        socketIOServer.addConnectListener(null);
//        socketIOServer.addDisconnectListener(null);
        return socketIOServer;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }


}
