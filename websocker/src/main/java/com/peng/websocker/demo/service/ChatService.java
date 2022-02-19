package com.peng.websocker.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.peng.websocker.demo.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${redis.channel.msgToAll}")
    private String msgToAll;

    @Value("${redis.channel.noticeToAll}")
    private String noticeToAll;

    /**
     * 分布式发送消息
     */
    public void sendRemoteMessage(ChatMessage chatMessage) {
        redisTemplate.convertAndSend(msgToAll, JSONObject.toJSONString(chatMessage));
    }

    public void sendRemoteNotice(ChatMessage chatMessage) {
        redisTemplate.convertAndSend(noticeToAll, JSONObject.toJSONString(chatMessage));
    }

    /**
     * 给本项目连接用户发送消息
     *
     * @param message
     */
    public void sendLocalMessage(ChatMessage message) {
        messageTemplate.convertAndSend("/topic/public", message);
    }

    public void sendMessageToUser(ChatMessage message){
        messageTemplate.convertAndSendToUser("","",message);
    }
}
