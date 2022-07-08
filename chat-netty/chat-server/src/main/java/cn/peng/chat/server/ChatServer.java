package cn.peng.chat.server;

import cn.peng.chat.common.handler.RequestCodec;
import cn.peng.chat.common.handler.ResponseCodec;
import cn.peng.chat.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class ChatServer {
    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        EventLoopGroup businessWorker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap().group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new IdleStateHandler(60, 60, 120))
                                .addLast(new RequestCodec())
                                .addLast(new ResponseCodec())
                                .addLast(businessWorker, new ServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 2048)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

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
