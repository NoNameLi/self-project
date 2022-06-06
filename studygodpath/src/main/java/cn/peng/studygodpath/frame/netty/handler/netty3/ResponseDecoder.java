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
        int readerIndex = buffer.readerIndex();
        Response response = new Response();
        response.setModule(buffer.readShort());
        response.setCmd(buffer.readShort());
        response.setStateCode(buffer.readInt());
        int dataLength = buffer.readInt();

        int dataNum = buffer.readableBytes();
        if (dataNum < dataLength) { // 数据不够 还原读指针  可以把 length data 提到包对象中，再加个length 偏移量，在父类中进行数据包的判读
            buffer.readerIndex(readerIndex);
            return null;
        }
        response.setData(dataLength > 0 ? buffer.readBytes(dataLength).array() : new byte[0]);
        return response;
    }

    @Override
    public int minPackageLength() {
        return Response.MIN_LENGTH;
    }

    @Override
    public int loadDataLengthOffset() {
        return Response.DATA_LENGTH_OFFSET;
    }

}
