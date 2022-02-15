package cn.peng.webflux.config;


import cn.peng.webflux.handle.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebFluxRoutingConfiguration {

    @Autowired
    private UserHandler userHandler;

    @Bean("routerFunction")
    public RouterFunction<ServerResponse> routerFunction() {

        return route(GET("/webFlux/user/{id}"), userHandler::get)
                .andRoute(GET("/webFlux/users"), userHandler::listAll)
                .andRoute(POST("/webFlux/user"), userHandler::create)
                .andRoute(PUT("/webFlux/user"), userHandler::update);
    }

}

