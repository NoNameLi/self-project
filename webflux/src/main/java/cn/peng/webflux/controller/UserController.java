package cn.peng.webflux.controller;


import cn.peng.webflux.entity.User;
import cn.peng.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Collection;

@RestController
@RequestMapping("/annotated")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User get(@PathVariable Long id) {
        return userRepository.getById(id);
    }

    @GetMapping("/users")
    public Collection<User> user() {
        log.info("开始");
        Collection<User> users = userRepository.listAll();
        log.info("完成");
        return users;
    }

    @GetMapping("/userFlux")
    public Flux<User> userFlux() {
        log.info("Flux开始");
        Flux<User> userFlux = Flux.fromIterable(userRepository.listAll());
        log.info("Flux结束");
        return userFlux;
    }

    @PostMapping("/user")
    public void create(@RequestBody User user) {
        userRepository.createUser(user);
    }

    @PutMapping("/user")
    public void update(@RequestBody User user) {
        userRepository.updateUser(user);
    }
}
