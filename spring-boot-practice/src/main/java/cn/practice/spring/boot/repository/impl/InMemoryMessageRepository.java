package cn.practice.spring.boot.repository.impl;

import cn.practice.spring.boot.model.Message;
import cn.practice.spring.boot.repository.MessageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-11 15:53
 * QDescription:
 */
public class InMemoryMessageRepository implements MessageRepository {

    private AtomicLong count = new AtomicLong();
    private Map<Long, Message> map = new HashMap<>();

    @Override
    public Iterable<Message> findAll() {
        return map.values();
    }

    @Override
    public Message get(Long id) {
        return map.get(id);
    }

    @Override
    public Message save(Message message) {
        if (null == message.getId()) {
            message.setId(count.incrementAndGet());
        }
        map.put(message.getId(), message);
        return message;
    }

    @Override
    public void delete(Long id) {
        this.map.remove(id);
    }
}
