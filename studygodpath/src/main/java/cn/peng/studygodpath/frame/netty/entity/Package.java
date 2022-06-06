package cn.peng.studygodpath.frame.netty.entity;

import lombok.Data;

@Data
public class Package {
    public static int packageHead = -1578486318;

    private byte[] data;

    public int getDataLength() {
        return null == data ? 0 : data.length;
    }
}
