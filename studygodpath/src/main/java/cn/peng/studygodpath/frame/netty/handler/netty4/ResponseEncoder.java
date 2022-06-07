package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Request 编码器
 */
public class ResponseEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        Response response = (Response) msg;
        out.writeInt(response.packageHead);
        out.writeShort(response.getModule());
        out.writeShort(response.getCmd());
        out.writeInt(response.getStateCode());
        out.writeInt(response.getDataLength());
        if (null != response.getData()) {
            out.writeBytes(response.getData());
        }
    }
}
