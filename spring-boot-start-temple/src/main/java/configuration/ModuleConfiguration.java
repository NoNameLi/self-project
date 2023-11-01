package configuration;


import annotation.ConditionOnModuleEnable;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import propertie.MoudlePropertie;

@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionOnModuleEnable
@EnableConfigurationProperties(MoudlePropertie.class)
public class ModuleConfiguration {

    @PostConstruct
    public void init(){
        log.info("[project] |- SDK [module] Auto Configure.");
    }
}
