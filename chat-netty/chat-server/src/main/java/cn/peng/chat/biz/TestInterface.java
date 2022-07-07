package cn.peng.chat.biz;

import cn.peng.chat.common.annotation.Cmd;
import cn.peng.chat.common.annotation.Model;

@Model(model = 1)
public interface TestInterface {

    @Cmd(cmd = 1)
    public boolean testMethod();

}
