package configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@Slf4j
@AutoConfiguration
@Import({ModuleConfiguration.class})
public class ModuleAutoConfiguration {

    @PostConstruct
    public void init(){
        log.info("[project] |- Module [Module Starter] Auto Configure.");
    }
}
