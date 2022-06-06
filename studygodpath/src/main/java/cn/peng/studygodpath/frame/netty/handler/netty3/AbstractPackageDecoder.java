package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Package;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import java.util.Arrays;

/**
 * netty3 自定义数据包抽象解码器
 */
public abstract class AbstractPackageDecoder extends FrameDecoder {
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer buffer) {
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
                System.out.println("没有找到包头");
                return null;
            }
        }
        // 已经读取过包头
        int packageLength = buffer.readableBytes();
        if (packageLength >= this.minPackageLength()) {
            // 标记一下
            buffer.markReaderIndex();
            // 跳到负载数据长度位置
            buffer.readerIndex(buffer.readerIndex() + loadDataLengthOffset());
            // 负载数据长度
            int loadDataLength = buffer.readInt();
            int dataLength = buffer.readableBytes();
            // 还原读位置，不管够不够都要还原
            buffer.resetReaderIndex();
            if (loadDataLength <= dataLength) {
                return this.readPackage(buffer);
            }
        }
        // 数据不够，等待
        return null;
    }

    /**
     * 满足最小数据长度读取，需要自行处理业务长度
     */
    public abstract Object readPackage(ChannelBuffer buffer);

    public abstract int minPackageLength();

    public abstract int loadDataLengthOffset();

}
