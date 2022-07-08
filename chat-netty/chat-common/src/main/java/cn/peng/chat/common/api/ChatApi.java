package cn.peng.chat.common.api;

import cn.peng.chat.common.annotation.Cmd;
import cn.peng.chat.common.annotation.Model;

@Model(2)
public interface ChatApi {

    @Cmd(1)
    void worldChat();

    @Cmd(2)
    void privateChat();
}
