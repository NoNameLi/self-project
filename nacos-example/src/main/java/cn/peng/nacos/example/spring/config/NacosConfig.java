package cn.peng.nacos.example.spring.config;


import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))//启用 Nacos Spring 的配置管理服务
@NacosPropertySource(dataId = "JDBC_ADDRESS", groupId = "dev", autoRefreshed = true)
public class NacosConfig {
}
