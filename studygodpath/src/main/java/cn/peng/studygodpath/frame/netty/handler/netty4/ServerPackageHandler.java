package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Request;
import cn.peng.studygodpath.frame.netty.entity.Response;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-07 17:04
 * QDescription:
 */
public class ServerPackageHandler extends SimpleChannelInboundHandler<Request> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        System.out.println(request.toString());
        Response response = null;
        if (request.getModule() == (short) 1) {
            switch (request.getCmd()) {
                case (short) 1:
                    response = Response.of(request, 1, "文石".getBytes(StandardCharsets.UTF_8));
                    break;
                case (short) 2:
                    response = Response.of(request, 1, "我爱你".getBytes(StandardCharsets.UTF_8));
                    break;
            }
        }
        if (response == null)
            response = Response.of(request, 0, null);
        ctx.channel().writeAndFlush(response);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + ":" + event.state());
            if (event.state() == IdleState.ALL_IDLE) {
                ChannelFuture closeFuture = ctx.channel().writeAndFlush(Response.of((short) 0, (short) 1, (short) 1, "idle connect,will close".getBytes()));
                closeFuture.addListener((ChannelFutureListener) future -> future.channel().close());
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
