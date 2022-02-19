package com.peng.websocker.demo.controller;

import com.peng.websocker.demo.entity.ChatMessage;
import com.peng.websocker.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-19 12:39
 * QDescription:
 */
@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        chatService.sendRemoteMessage(chatMessage);
    }

    @MessageMapping("chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatService.sendRemoteNotice(chatMessage);
    }
}
