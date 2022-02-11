package cn.practice.spring.boot.repository;


import cn.practice.spring.boot.model.Message;

import java.util.List;

public interface MessageRepository {

    Iterable<Message> findAll();

    Message get(Long id);

    Message save(Message message);

    void delete(Long id);
}
