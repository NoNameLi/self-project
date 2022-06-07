package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.handler.netty4.ServerTextHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class Netty4Client {

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
                    .addLast(new StringEncoder()).addLast(new StringDecoder()).addLast(new ServerTextHandler());
        }
    };


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(loopGroup).channel(NioSocketChannel.class)
                    .handler(simpleTextChannelInitializer)
//                    .handler(customPackageChannelInitializer)
            ;
            ChannelFuture channelFuture = bootstrap.connect("192.168.0.105", 1000).sync();
            channelFuture.channel().writeAndFlush("hello server! I am client");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
