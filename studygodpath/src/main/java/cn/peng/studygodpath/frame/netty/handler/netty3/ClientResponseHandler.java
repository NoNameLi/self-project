package cn.peng.studygodpath.frame.netty.handler.netty3;

import cn.peng.studygodpath.frame.netty.entity.Response;
import org.jboss.netty.channel.*;

import java.nio.charset.StandardCharsets;

public class ClientResponseHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Response response = (Response) e.getMessage();
        System.out.println(response.toString());
        System.out.println(new String(response.getData(), StandardCharsets.UTF_8));
        //业务操作
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
