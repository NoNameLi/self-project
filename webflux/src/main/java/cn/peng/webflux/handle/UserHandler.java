package cn.peng.webflux.handle;


import cn.peng.webflux.entity.User;
import cn.peng.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Component
@Slf4j
public class UserHandler {
    private final UserRepository userRepository;

    public Mono<ServerResponse> get(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just(userRepository.getById(Long.valueOf(request.pathVariable("id")))), User.class);
    }

    public Mono<ServerResponse> listAll(ServerRequest request) {
        log.info("函数式端点开始");
        Mono<ServerResponse> responseMono = ServerResponse.ok().body(Flux.fromIterable(userRepository.listAll()), User.class);
        log.info("函数式端点结束");
        return responseMono;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        userRepository.createUser(request.bodyToMono(User.class).block());
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        userRepository.updateUser(request.bodyToMono(User.class).block());
        return ServerResponse.ok().build();
    }
}
