package cn.practice.spring.boot.controller.rest.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @GetMapping("/load")
    public void loadAgent(@RequestParam("path") String path,
                          @RequestParam("args") String args) throws Exception {
//        VirtualMachine machine = VirtualMachine.attach(getCurrentPid());
//        machine.loadAgent(path, args);
//        machine.detach();
    }

    @GetMapping("/restore")
    public void restore(@RequestParam("args") String args) {

    }

    public String getCurrentPid() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getName().split("@")[0];
    }

}
