package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Request 编码器
 */
public class RequestEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) {
        Request request = (Request) msg;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(request.packageHead);
        channelBuffer.writeShort(request.getModule());
        channelBuffer.writeShort(request.getCmd());
        channelBuffer.writeInt(request.getDataLength());
        if (null != request.getData()) {
            channelBuffer.writeBytes(request.getData());
        }
        return channelBuffer.array();
    }
}
