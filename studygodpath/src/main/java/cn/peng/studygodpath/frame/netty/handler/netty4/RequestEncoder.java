package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Request 编码器
 */
public class RequestEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        Request request = (Request) msg;
        out.writeInt(request.packageHead);
        out.writeShort(request.getModule());
        out.writeShort(request.getCmd());
        out.writeInt(request.getDataLength());
        if (null != request.getData()) {
            out.writeBytes(request.getData());
        }
    }
}
