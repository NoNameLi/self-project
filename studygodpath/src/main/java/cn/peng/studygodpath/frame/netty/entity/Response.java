package cn.peng.studygodpath.frame.netty.entity;

public class Response extends Package {

    public static int MIN_LENGTH = 4 + 2 + 2 + 4 + 4;

    private short module;

    private short cmd;

    private int stateCode;

    private byte[] data;

    public int getDataLength() {
        return null == data ? 0 : data.length;
    }
}
