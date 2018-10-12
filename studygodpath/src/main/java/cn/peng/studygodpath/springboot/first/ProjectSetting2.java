package cn.peng.studygodpath.springboot.first;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;

import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * Created by remote on 2018/3/3.
 */
public class ProjectSetting2 implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private ResourceLoader loader = new DefaultResourceLoader();

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        Resource resource = loader.getResource("classpath:project2.properties");
        PropertySource<?> propertySource = null;
        try {
            propertySource = new PropertySourcesLoader().load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        applicationEnvironmentPreparedEvent.getEnvironment().getPropertySources().addLast(propertySource);

    }
}
