package cn.peng.studygodpath.frame.netty.handler.netty4;

import cn.peng.studygodpath.frame.netty.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-07 17:07
 * QDescription:
 */
public class ClientPackageHandler extends SimpleChannelInboundHandler<Response> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        System.out.println(msg.toString());
        System.out.println(new String(msg.getData(), StandardCharsets.UTF_8));

    }
}
