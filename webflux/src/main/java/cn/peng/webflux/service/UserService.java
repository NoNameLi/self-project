package cn.peng.webflux.service;


import cn.peng.webflux.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> getById(Mono<Long> id);

    Flux<User> listAll();

    Mono<Void> createUser(Mono<User> userMono);

    Mono<Void> updateUser(Mono<User> userMono);

}
