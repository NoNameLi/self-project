package propertie;

import constans.ModuleContstans;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(ModuleContstans.MODULE_PROPERTIE)
public class MoudlePropertie {

    private Boolean enable;
    private String key1;
    private String key2;
}
