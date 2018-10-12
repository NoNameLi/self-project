package cn.peng.minispring.controller;

import cn.peng.minispring.annotation.Controller;
import cn.peng.minispring.annotation.Qualifier;
import cn.peng.minispring.annotation.RequestMapping;
import cn.peng.minispring.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

    @Qualifier
    private TestService testService;

    @RequestMapping("/base")
    public void baseTest() {
        System.out.println("controller test success");
        testService.baseTest();
    }
}
