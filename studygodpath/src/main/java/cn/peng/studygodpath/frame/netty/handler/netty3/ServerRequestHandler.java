package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Request;
import cn.peng.studygodpath.frame.netty.entity.Response;
import org.jboss.netty.channel.*;

import java.nio.charset.StandardCharsets;

public class ServerRequestHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Request t = (Request) e.getMessage();
        System.out.println(t.toString());
        Response response = null;
        if (t.getModule() == (short) 1) {
            switch (t.getCmd()) {
                case (short) 1:
                    response = Response.of(t, 1, "文石".getBytes(StandardCharsets.UTF_8));
                    break;
                case (short) 2:
                    response = Response.of(t, 1, "我爱你".getBytes(StandardCharsets.UTF_8));
                    break;
            }
        }
        if (response == null)
            response = Response.of(t, 0, null);
        ctx.getChannel().write(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelOpen(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
    }
}
