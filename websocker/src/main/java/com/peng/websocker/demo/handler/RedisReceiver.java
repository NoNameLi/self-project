package com.peng.websocker.demo.handler;

import com.alibaba.fastjson.JSONObject;
import com.peng.websocker.demo.entity.ChatMessage;
import com.peng.websocker.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-19 15:23
 * QDescription:
 */
@Component
public class RedisReceiver {

    @Autowired
    private ChatService chatService;

    public void msgToAllHandle(String message) {
        ChatMessage chatMessage = JSONObject.parseObject(message, ChatMessage.class);
        chatService.sendLocalMessage(chatMessage);
    }

    public void noticeToAllHandle(String message) {
        ChatMessage chatMessage = JSONObject.parseObject(message, ChatMessage.class);
        chatService.sendLocalMessage(chatMessage);
    }
}
