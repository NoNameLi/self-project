package cn.peng.chat.server;

import cn.peng.chat.common.handler.RequestDecode;
import cn.peng.chat.common.handler.ResponseEncode;
import cn.peng.chat.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;

public class ChatServer {

    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();


        ServerBootstrap bootstrap = new ServerBootstrap().group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(60, 60, 120))
                                .addLast(new RequestDecode())
                                .addLast(new ResponseEncode())
                                .addLast(new ServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 2048)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);

        try {
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(8888)).sync();
            System.out.println("server success start....");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
