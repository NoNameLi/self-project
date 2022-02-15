package cn.peng.webflux.service.impl;


import cn.peng.webflux.entity.User;
import cn.peng.webflux.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserServiceImpl implements UserService {

    private AtomicLong count = new AtomicLong();
    private Map<Long, User> userRepository = new HashMap<>();

    public UserServiceImpl() {
        long l = count.incrementAndGet();
        userRepository.put(l, new User(l, "小明", 10));

        l = count.incrementAndGet();
        userRepository.put(l, new User(l, "小红", 9));

        l = count.incrementAndGet();
        userRepository.put(l, new User(l, "小鹏", 8));
    }


    @Override
    public Mono<User> getById(Mono<Long> id) {
        return null;
    }

    @Override
    public Flux<User> listAll() {
        return null;
    }

    @Override
    public Mono<Void> createUser(Mono<User> userMono) {
        return null;
    }

    @Override
    public Mono<Void> updateUser(Mono<User> userMono) {
        return null;
    }
}

