package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Request;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Request 解码器
 * 校验寻找包头
 * 校验数据长度
 */
public class RequestDecoder extends AbstractPackageDecoder {


    @Override
    public Object readPackage(ChannelBuffer buffer) {
        Request request = new Request();
        request.setModule(buffer.readShort());
        request.setCmd(buffer.readShort());
        int dataLength = buffer.readInt();
        request.setData(dataLength > 0 ? buffer.readBytes(dataLength).array() : new byte[0]);
        return request;
    }

    @Override
    public int minPackageLength() {
        return Request.MIN_LENGTH;
    }

    @Override
    public int loadDataLengthOffset() {
        return Request.DATA_LENGTH_OFFSET;
    }

}
