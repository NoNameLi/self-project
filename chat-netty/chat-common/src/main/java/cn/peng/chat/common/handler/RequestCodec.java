package cn.peng.chat.common.handler;

import cn.peng.chat.common.data.Package;
import cn.peng.chat.common.data.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class RequestCodec extends AbstractPackageCodec<Request> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Request request, ByteBuf out) {
        out.writeInt(Package.packageHead)
                .writeShort(request.getModule())
                .writeShort(request.getCmd())
                .writeInt(request.getDataLength())
                .writeBytes(request.getData());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (findPackageHead(in)) {
            if (in.readableBytes() >= Request.NOT_PACKAGE_HEAD_MIN_LENGTH) {
                in.markReaderIndex();
                in.readerIndex(in.readerIndex() + Request.DATA_LENGTH_OFFSET);
                int length = in.readInt();
                int dataLength = in.readableBytes();
                in.resetReaderIndex();
                if (dataLength >= length) {
                    out.add(Request.of(in.readShort(), in.readShort(), in.readBytes(length).array()));
                }
            }
        }
    }
}
