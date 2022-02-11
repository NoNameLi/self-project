package cn.practice.spring.boot.controller.rest.v1;

import cn.practice.spring.boot.controller.rest.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TestController {

    @GetMapping("/index")
    public ApiResponse indexHello() {
        return ApiResponse.success();
    }


}
