package cn.peng.studygodpath.frame.netty.handler;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Netty3ServerHandler extends SimpleChannelHandler {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println(e.getMessage());
        ctx.getChannel().write("client hi");
        super.messageReceived(ctx, e);
    }

    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof IdleStateEvent) {
            IdleStateEvent se = (IdleStateEvent) e;
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + ":" + se.getState());
            if (se.getState() == IdleState.ALL_IDLE) {
                ChannelFuture channelFuture = ctx.getChannel().write("you is idea,system auto close connect");
                channelFuture.addListener(future -> future.getChannel().close());
            }
        } else {
            super.handleUpstream(ctx, e);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
    }

    @Override
    public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.disconnectRequested(ctx, e);
    }

    @Override
    public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.closeRequested(ctx, e);
    }
}
