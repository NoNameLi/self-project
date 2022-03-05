package com.peng.websocker.demo.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "socketio")
public class SocketIoProperties {
    private String host;
    private int port;
    private int bossThreads;
    private int workerThreads;
    private boolean isAllowCustomRequests;
    private int upgradeTimeout;
    private int pingTimeout;
    private int pingInterval;
}
