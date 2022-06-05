package cn.peng.studygodpath.frame.netty.entity;


import lombok.Data;

/**
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头	| 模块号  | 命令号 |  长度  |   数据  |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 */
@Data
public class Request extends Package {

    public static int MIN_LENGTH = 4 + 2 + 2 + 4;

    private short module;

    private short cmd;

    private byte[] data;

    public int getDataLength() {
        return null == data ? 0 : data.length;
    }
}
