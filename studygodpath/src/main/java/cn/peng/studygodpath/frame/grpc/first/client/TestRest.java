package cn.peng.studygodpath.frame.grpc.first.client;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("grpc")
@RequiredArgsConstructor
public class TestRest {

    private final TestService testService;

    @GetMapping("/test")
    public String test(@RequestParam("name") String name) {
        return testService.receiveGreeting(name);
    }
}
