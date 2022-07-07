package cn.peng.chat.common.dto;

import lombok.Data;

@Data
public abstract class Package {
    public static int packageHead = -1578486318;

    private short module;

    private short cmd;

    private byte[] data;

    public int getDataLength() {
        return null == data ? 0 : data.length;
    }

}
