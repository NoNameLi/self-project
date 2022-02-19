package com.peng.websocker.demo.listener;

import com.peng.websocker.demo.entity.ChatMessage;
import com.peng.websocker.demo.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-19 12:47
 * QDescription:
 */
@Component
@Slf4j
public class WebSockerEventListener {

    @Resource
    private ChatService chatService;

    @EventListener
    public void handleWebSockerConnectListener(SessionConnectedEvent event) {
        log.info("received a new web connection");
    }

    @EventListener
    public void handleWebScokerCloseListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (Objects.nonNull(username)) {
            chatService.sendRemoteNotice(new ChatMessage().setType(ChatMessage.MessageType.LEAVE).setSender(username));
        }

    }
}
