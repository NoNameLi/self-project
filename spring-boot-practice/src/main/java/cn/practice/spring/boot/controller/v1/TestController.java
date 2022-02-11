package cn.practice.spring.boot.controller.v1;

import cn.practice.spring.boot.controller.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 11:10
 * QDescription:
 */
@RestController
public class TestController {

    @GetMapping("/")
    public ApiResponse indexHello() {
        return ApiResponse.success();
    }


}
