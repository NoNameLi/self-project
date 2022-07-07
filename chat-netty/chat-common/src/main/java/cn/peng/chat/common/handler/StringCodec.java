package cn.peng.chat.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class StringCodec extends ByteToMessageCodec<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in.readBytes(in.readableBytes()).toString(Charset.defaultCharset()));
    }
}
