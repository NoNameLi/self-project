package cn.peng.studygodpath.frame.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class Netty4ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接受到数据：" + msg);
//        ctx.writeAndFlush("hi client,I am server");
        ctx.channel().writeAndFlush("hi client,I am server");

    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("has client connect");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client connect disconnect");
        super.channelInactive(ctx);
    }
}
