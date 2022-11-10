package cn.peng.nacos.example.spring.service;


import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))//启用 Nacos Spring 的配置管理服务
@NacosPropertySource(dataId = "org.example", autoRefreshed = true)
public class NacosConfig {
}
