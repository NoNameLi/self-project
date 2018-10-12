package cn.peng.studygodpath.springboot.first;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by remote on 2018/3/3.
 * 项目的设置参数
 * 通过配置文件设置参数
 */


@Component
@ConfigurationProperties(prefix="project")
@PropertySource("classpath:project.properties")
public class ProjectSetting {


    @Value("${project.author}")
    private String author;


    public String getAuthor() {
        return author;
    }
}
