package cn.peng.chat.server.handler;

import cn.peng.chat.common.dto.Request;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class ServerHandler extends SimpleChannelInboundHandler<Request> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        // 根据model cmd 找到对应的业务处理方法 执行方法
        // 返回response 数据
        // 回写数
        // 发送聊天消息 需要保存用户的连接会话
        Channel channel = ctx.channel();
        Attribute<Object> userId = channel.attr(AttributeKey.newInstance("userId"));

    }
}
