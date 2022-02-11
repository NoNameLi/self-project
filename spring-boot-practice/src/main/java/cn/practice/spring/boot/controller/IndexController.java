package cn.practice.spring.boot.controller;

import cn.practice.spring.boot.controller.rest.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
    @GetMapping("/")
    public ApiResponse indexHello() {
        return ApiResponse.success();
    }
}
