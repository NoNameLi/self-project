package cn.peng.studygodpath.frame.netty.handler.netty4;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Netty4ServerHandler extends SimpleChannelInboundHandler<String> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println("接受到数据：" + msg);
        ctx.channel().writeAndFlush("hi client,I am server");
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + ":" + e.state());
            if (e.state() == IdleState.ALL_IDLE) {
                ChannelFuture channelFuture = ctx.channel().writeAndFlush("all idle ,system will close connect");
                channelFuture.addListener((ChannelFutureListener) future -> future.channel().close());
//            } else if (e.state() == IdleState.WRITER_IDLE) {
//                ctx.writeAndFlush(new PingMessage());
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
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
