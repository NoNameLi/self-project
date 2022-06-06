package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Response;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Request 解码器
 * 校验寻找包头
 * 校验数据长度
 */
public class ResponseDecoder extends AbstractPackageDecoder {

    @Override
    public Object readPackage(ChannelBuffer buffer) {
        Response response = new Response();
        response.setModule(buffer.readShort());
        response.setCmd(buffer.readShort());
        response.setStateCode(buffer.readInt());
        int dataLength = buffer.readInt();
        response.setData(dataLength > 0 ? buffer.readBytes(dataLength).array() : new byte[0]);
        return response;
    }

    @Override
    public int minPackageLength() {
        return Response.NOT_PACKAGE_HEAD_MIN_LENGTH;
    }

    @Override
    public int loadDataLengthOffset() {
        return Response.DATA_LENGTH_OFFSET;
    }

}
