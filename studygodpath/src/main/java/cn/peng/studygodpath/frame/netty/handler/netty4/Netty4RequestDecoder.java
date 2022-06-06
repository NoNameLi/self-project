package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Package;
import cn.peng.studygodpath.frame.netty.entity.Request;
import io.netty.buffer.ByteBuf;

/**
 * Request 解码器
 */
public class Netty4RequestDecoder extends AbstractPackageDecoder {

    @Override
    public Package readPackage(ByteBuf in) {
        Request request = new Request();
        request.setModule(in.readShort());
        request.setCmd(in.readShort());
        int dataLength = in.readInt();
        request.setData(dataLength > 0 ? in.readBytes(dataLength).array() : new byte[0]);
        return request;
    }

    @Override
    public int minPackageLength() {
        return Request.NOT_PACKAGE_HEAD_MIN_LENGTH;
    }

    @Override
    public int loadDataLengthOffset() {
        return Request.DATA_LENGTH_OFFSET;
    }
}
