package cn.peng.chat.common.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Response extends Package {

    public static int DATA_LENGTH_OFFSET = 8;

    public static int NOT_PACKAGE_HEAD_MIN_LENGTH = 2 + 2 + 4 + 4;

    private int stateCode;

    public static Response of(short module, short cmd, int stateCode, byte[] data) {
        Response response = new Response();
        response.setModule(module);
        response.setCmd(cmd);
        response.setStateCode(stateCode);
        response.setData(data);
        return response;
    }

    public static Response of(Request request, ResponseStateCode stateCode, byte[] data) {
        return Response.of(request.getModule(), request.getCmd(), stateCode.getValue(), data);
    }

}
