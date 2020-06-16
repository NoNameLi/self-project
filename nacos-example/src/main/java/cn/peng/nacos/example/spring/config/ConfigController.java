package cn.peng.nacos.example.spring.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("config")
public class ConfigController {

    //    @NacosInjected
//    private ConfigService configService;
//
//    @NacosValue(value = "${JDBC_ADDRESS}", autoRefreshed = true)//:127.0.0.1:3360
    private String JDBC_ADDRESS;

    //需要同时有 Setter方法才能在配置变更的时候自动更新。
    public void setJDBC_ADDRESS(String JDBC_ADDRESS) {
        this.JDBC_ADDRESS = JDBC_ADDRESS;
    }

    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    @ResponseBody
    public String getConfigValue() {
        return JDBC_ADDRESS;
    }

    @RequestMapping(value = "publishConfig", method = RequestMethod.POST)
    public ResponseEntity<String> publishConfig(String dataId, String group, String content) throws NacosException {
//        boolean publishResult = configService.publishConfig(dataId, group, content);
//        if (publishResult) {
//            return new ResponseEntity<String>("Success", HttpStatus.OK);
//        }
        return new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
