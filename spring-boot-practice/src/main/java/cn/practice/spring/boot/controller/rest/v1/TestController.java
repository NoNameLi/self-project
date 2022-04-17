package cn.practice.spring.boot.controller.rest.v1;

import cn.practice.spring.boot.controller.rest.agent.AgentController;
import cn.practice.spring.boot.controller.rest.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/rest")
public class TestController {
    Logger logger = LoggerFactory.getLogger(AgentController.class);

    @GetMapping("/index")
    public ApiResponse indexHello() {
        return ApiResponse.success();
    }

    @GetMapping("/mock")
    public ApiResponse mock(@RequestParam("str") String str, @RequestParam("integer") Integer integer) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return ApiResponse.success();
    }
}
