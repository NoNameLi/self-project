package com.peng.websocker.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-19 12:37
 * QDescription:
 */
@Data
@Accessors(chain = true)
public class ChatMessage {

    public enum MessageType {
        CHAT,//消息
        JOIN,//加入
        LEAVE //离开
    }

    private MessageType type;
    private String content;
    private String sender;
}
