package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Package;
import cn.peng.studygodpath.frame.netty.entity.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Request 解码器
 */
public class Netty4RequestDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        while (true) {
            if (in.readableBytes() > 4) {
                if (Package.packageHead == in.readInt())
                    break;
            } else {
                return;
            }
        }
        int readerIndex = in.readerIndex();
        int byteNum = in.readableBytes();
        if (byteNum >= Request.MIN_LENGTH) {
            Request request = new Request();
            request.setModule(in.readShort());
            request.setCmd(in.readShort());
            int dataLength = in.readInt();

            int dataNum = in.readableBytes();
            if (dataNum < dataLength) { // 数据不够 还原读指针
                in.readerIndex(readerIndex);
                return;
            }
            request.setData(dataLength > 0 ? in.readBytes(dataLength).array() : new byte[0]);
            out.add(request);
        }
        // 数据不够，等待
        return;
    }
}
