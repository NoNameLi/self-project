package cn.peng.chat.server.handler;

import cn.peng.chat.common.data.Request;
import cn.peng.chat.common.data.Response;
import cn.peng.chat.common.data.ResponseStateCode;
import cn.peng.chat.context.ModelCmdInvoke;
import cn.peng.chat.context.ModelCmdInvokeHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

public class ServerHandler extends SimpleChannelInboundHandler<Request> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        Channel channel = ctx.channel();
        ModelCmdInvoke modelCmdInvoke = ModelCmdInvokeHolder.get(msg.getModule(), msg.getCmd());
        if (null == modelCmdInvoke) {
            channel.writeAndFlush(Response.of(msg, ResponseStateCode.NotModelCmd, null));
        } else {
            Object userId = channel.attr(AttributeKey.newInstance("userId")).get();
            if (modelCmdInvoke.isNeedLogin() && null == userId) {
                channel.writeAndFlush(Response.of(msg, ResponseStateCode.NotModelCmd, null));
            }
            byte[] responseData = modelCmdInvoke.invoke(msg.getData());
            channel.writeAndFlush(Response.of(msg, ResponseStateCode.SUCCESS, responseData));
        }

    }
}
