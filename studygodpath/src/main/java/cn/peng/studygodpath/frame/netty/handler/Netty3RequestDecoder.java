package cn.peng.studygodpath.frame.netty.handler;

import cn.peng.studygodpath.frame.netty.entity.Package;
import cn.peng.studygodpath.frame.netty.entity.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * Request 解码器
 * 校验寻找包头
 * 校验数据长度
 */
public class Netty3RequestDecoder extends FrameDecoder {
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) {
        while (true) {
            if (buffer.readableBytes() > 4) {
                buffer.markReaderIndex();
                if (Package.packageHead == buffer.readInt()) {
                    break;
                }
                // 没有找到 应该偏移一个字节，并判断
                buffer.resetReaderIndex();
                buffer.readByte();
            } else {
                return null;
            }
        }
        int readerIndex = buffer.readerIndex();
        int byteNum = buffer.readableBytes();
        if (byteNum >= Request.MIN_LENGTH) {
            Request request = new Request();
            request.setModule(buffer.readShort());
            request.setCmd(buffer.readShort());
            int dataLength = buffer.readInt();

            int dataNum = buffer.readableBytes();
            if (dataNum < dataLength) { // 数据不够 还原读指针
                buffer.readerIndex(readerIndex);
                return null;
            }
            request.setData(dataLength > 0 ? buffer.readBytes(dataLength).array() : new byte[0]);
            return request;
        }
        // 数据不够，等待
        return null;
    }
}
