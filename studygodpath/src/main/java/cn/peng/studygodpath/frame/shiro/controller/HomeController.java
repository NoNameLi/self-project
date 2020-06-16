package cn.peng.studygodpath.frame.shiro.controller;

import cn.peng.studygodpath.frame.shiro.PasswordHelper;
import cn.peng.studygodpath.frame.shiro.entity.User;
import cn.peng.studygodpath.frame.shiro.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping("login")
    public String login() {
        return "Here is Login page";
    }

    @GetMapping("unauthc")
    public String unauthc() {
        return "Here is Unauthc page";
    }

    @GetMapping("doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);
        subject.login(authenticationToken);
        User user = userService.findUserByName(username);
        subject.getSession().setAttribute("user", user);
        return "success";
    }

    @GetMapping("register")
    public Object register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        passwordHelper.encryptPassword(user);

        userService.saveUser(user);
        return "SUCCESS";
    }
}
