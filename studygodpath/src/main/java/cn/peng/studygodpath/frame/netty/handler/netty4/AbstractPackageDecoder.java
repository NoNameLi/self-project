package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Package;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public abstract class AbstractPackageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (true) {
            if (in.readableBytes() > 4) {
                in.markReaderIndex();
                if (Package.packageHead == in.readInt()) {
                    break;
                }
                // 没有找到 应该偏移一个字节，并判断
                in.resetReaderIndex();
                in.readByte();
            } else {
                System.out.println("没有找到包头");
                return;
            }
        }
        // 已经读取过包头
        int packageLength = in.readableBytes();
        if (packageLength >= this.minPackageLength()) {
            // 标记一下
            in.markReaderIndex();
            // 跳到负载数据长度位置
            in.readerIndex(in.readerIndex() + loadDataLengthOffset());
            // 负载数据长度
            int loadDataLength = in.readInt();
            int dataLength = in.readableBytes();
            // 还原读位置，不管够不够都要还原
            in.resetReaderIndex();
            if (loadDataLength <= dataLength) {
                out.add(this.readPackage(in));
            }
        }
        // 数据不够，等待
        return;
    }

    /**
     * 满足最小数据长度读取，需要自行处理业务长度
     */
    public abstract Package readPackage(ByteBuf in);

    public abstract int minPackageLength();

    public abstract int loadDataLengthOffset();


}
