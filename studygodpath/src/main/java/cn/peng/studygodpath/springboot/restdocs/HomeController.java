package cn.peng.studygodpath.springboot.restdocs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by remote on 2018/3/22.
 */
@RestController
public class HomeController {


    @GetMapping("/")
    public Map<String,Object> greeting(){
        return Collections.singletonMap("message","Hello World");
    }
}
