package cn.peng.chat.biz;

import cn.peng.chat.common.annotation.Cmd;
import cn.peng.chat.common.annotation.Model;

@Model(1)
public interface TestInterface {

    @Cmd(1)
    public boolean testMethod();

}
