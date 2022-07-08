package cn.peng.chat.common.api;

import cn.peng.chat.common.annotation.Cmd;
import cn.peng.chat.common.annotation.Model;

@Model(value = 1, needLogin = false)
public interface UserApi {

    @Cmd(1)
    boolean register();

    @Cmd(2)
    boolean login();
}
