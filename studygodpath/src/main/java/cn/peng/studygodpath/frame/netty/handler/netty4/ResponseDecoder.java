package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Package;
import cn.peng.studygodpath.frame.netty.entity.Response;
import io.netty.buffer.ByteBuf;

/**
 * Request 解码器
 */
public class ResponseDecoder extends AbstractPackageDecoder {

    @Override
    public Package readPackage(ByteBuf in) {
        Response response = new Response();
        response.setModule(in.readShort());
        response.setCmd(in.readShort());
        response.setStateCode(in.readInt());
        int dataLength = in.readInt();
        response.setData(dataLength > 0 ? in.readBytes(dataLength).array() : new byte[0]);
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
