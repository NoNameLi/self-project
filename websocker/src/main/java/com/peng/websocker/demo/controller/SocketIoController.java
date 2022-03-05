package com.peng.websocker.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.protocol.Event;
import com.peng.websocker.demo.entity.ChatObject;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Administrator
 * @CreateTime:2022-03-04 15:20
 * QDescription:
 */
@Controller
public class SocketIoController {

    private static final ConcurrentHashMap<Long, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {

    }

    @OnDisconnect
    public void onDisconnnect(SocketIOClient client) {

    }

    @OnEvent("chatevent")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, ChatObject data) {
        System.out.println(JSONObject.toJSONString(data));
    }

    private void sendBroadcast(String name, String msg) {
        clientMap.values().stream().forEach(client ->
                client.sendEvent(name, msg));
    }
}
