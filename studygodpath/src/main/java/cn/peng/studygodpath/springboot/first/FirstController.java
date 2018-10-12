package cn.peng.studygodpath.springboot.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by remote on 2018/1/8.
 */
@RestController

public class FirstController {

    @Autowired
    private ProjectSetting projectSetting;

    @Autowired
    private Environment env;

    @RequestMapping("/first")
    public String helloSpringBoot() {
        return "Spring Boot to you Hello, project author:" + projectSetting.getAuthor() + " name:" + env.getProperty("project.name");
    }
}
