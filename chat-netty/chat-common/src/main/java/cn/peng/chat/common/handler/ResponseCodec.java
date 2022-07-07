package cn.peng.chat.common.handler;

import cn.peng.chat.common.dto.Package;
import cn.peng.chat.common.dto.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class ResponseCodec extends AbstractPackageCodec<Response> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf out) throws Exception {
        out.writeInt(Package.packageHead)
                .writeShort(response.getModule())
                .writeShort(response.getCmd())
                .writeInt(response.getStateCode())
                .writeInt(response.getDataLength())
                .writeBytes(response.getData());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (findPackageHead(in)) {
            if (in.readableBytes() >= Response.NOT_PACKAGE_HEAD_MIN_LENGTH) {
                in.markReaderIndex();
                in.readerIndex(in.readInt() + Response.DATA_LENGTH_OFFSET);
                int length = in.readInt();
                int dataLength = in.readableBytes();
                in.resetReaderIndex();
                if (dataLength >= length) {
                    out.add(Response.of(in.readShort(), in.readShort(), in.readInt(), in.readBytes(length).array()));
                }
            }
        }
    }
}
