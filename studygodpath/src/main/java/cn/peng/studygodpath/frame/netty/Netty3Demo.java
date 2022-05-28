package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.handler.PrintHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class Netty3Demo {

    public static void main(String[] args) {
        ServerBootstrap server = new ServerBootstrap();
        server.setFactory(new NioServerSocketChannelFactory());
        server.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast("handler", new PrintHandler());
            return pipeline;
        });
        server.bind(new InetSocketAddress(1000));
    }


//    public static void main(String[] args) throws InterruptedException {
//        ServerBootstrap server = new ServerBootstrap();
//        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workerGroup = new NioEventLoopGroup(10);
//        server.group(boosGroup, workerGroup)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new DiscardServerHandler());
//                    }
//                }).option(ChannelOption.SO_BACKLOG, 128)
//                .childOption(ChannelOption.SO_KEEPALIVE, true);
//        server.bind(1000).sync();
//    }


}
