package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.handler.netty4.RequestDecoder;
import cn.peng.studygodpath.frame.netty.handler.netty4.ResponseEncoder;
import cn.peng.studygodpath.frame.netty.handler.netty4.ServerPackageHandler;
import cn.peng.studygodpath.frame.netty.handler.netty4.ServerTextHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class Netty4Server {

    static ChannelInitializer simpleTextChannelInitializer = new ChannelInitializer<NioSocketChannel>() {
        @Override
        protected void initChannel(NioSocketChannel ch) {// pipeline handler 顺序
            ch.pipeline().addLast(new IdleStateHandler(5, 5, 10))
                    .addLast(new StringEncoder()).addLast(new StringDecoder()).addLast(new ServerTextHandler());
        }
    };

    static ChannelInitializer customPackageChannelInitializer = new ChannelInitializer<NioSocketChannel>() {
        @Override
        protected void initChannel(NioSocketChannel ch) {// pipeline handler 顺序
            ch.pipeline().addLast(new IdleStateHandler(5, 5, 10))
                    .addLast(new RequestDecoder()).addLast(new ResponseEncoder()).addLast(new ServerPackageHandler());
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap server = new ServerBootstrap();
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);
        try {
            server.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
//                    .childHandler(simpleTextChannelInitializer)
                    .childHandler(customPackageChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG, 2048)// 半链接的队列长度，还没完成三次握手的链接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);
            ChannelFuture channelFuture = server.bind(1000).sync();
            System.out.println("server start....");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
