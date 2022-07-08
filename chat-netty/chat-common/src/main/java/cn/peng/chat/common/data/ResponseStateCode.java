package cn.peng.chat.common.data;

import lombok.Getter;

@Getter
public enum ResponseStateCode {

    SUCCESS(1, "成功"),

    NotModelCmd(-100, "无对应的模块、指令"),

    UserNoLogin(-101, "用户未登录");


    private int value;
    private String desc;

    ResponseStateCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
