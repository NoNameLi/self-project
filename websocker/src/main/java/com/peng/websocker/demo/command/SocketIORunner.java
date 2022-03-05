package com.peng.websocker.demo.command;

import com.corundumstudio.socketio.SocketIOServer;
import com.sun.istack.internal.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public final class SocketIORunner implements CommandLineRunner {
    private final SocketIOServer server;

    public SocketIORunner(SocketIOServer server) {
        this.server = server;
    }

    public void run(@NotNull String... args) {
        this.server.start();
    }
}
