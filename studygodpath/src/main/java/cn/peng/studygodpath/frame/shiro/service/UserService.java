package cn.peng.studygodpath.frame.shiro.service;

import cn.peng.studygodpath.frame.shiro.entity.User;

public class UserService {
    public User findUserByName(String userName) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword("123456");
        return user;
    }

    public void saveUser(User user) {
    }
}
