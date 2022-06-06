package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Response;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Request 编码器
 */
public class ResponseEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) {
        Response response = (Response) msg;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        channelBuffer.writeInt(response.packageHead);
        channelBuffer.writeShort(response.getModule());
        channelBuffer.writeShort(response.getCmd());
        channelBuffer.writeInt(response.getStateCode());
        channelBuffer.writeInt(response.getDataLength());
        if (null != response.getData()) {
            channelBuffer.writeBytes(response.getData());
        }
        return channelBuffer;
    }
}
