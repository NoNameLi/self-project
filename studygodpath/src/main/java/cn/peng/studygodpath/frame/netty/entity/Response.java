package cn.peng.studygodpath.frame.netty.entity;

import lombok.Data;

/**
 * <pre>
 * 数据包格式
 * +——----——+——-----—+——----—+——-----——+——---+——------+
 * | 包头	| 模块号  | 命令号 |  状态码  |长度  |   数据  |
 * +——----——+——-----—+——----—+——-----——+——---+——------+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 状态码4字节int
 * 长度4字节(描述数据部分字节长度)
 */

@Data
public class Response extends Package {

    public static int MIN_LENGTH = 4 + 2 + 2 + 4 + 4;

    public static int DATA_LENGTH_OFFSET = 8;

    private short module;

    private short cmd;

    private int stateCode;

}
