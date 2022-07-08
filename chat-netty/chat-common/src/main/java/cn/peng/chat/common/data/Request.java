package cn.peng.chat.common.data;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Request extends Package {

    public static int DATA_LENGTH_OFFSET = 4;

    public static int NOT_PACKAGE_HEAD_MIN_LENGTH = 2 + 2 + 4;

    public static Request of(short module, short cmd, byte[] data) {
        Request request = new Request();
        request.setModule(module);
        request.setCmd(cmd);
        request.setData(data);
        return request;
    }

}
