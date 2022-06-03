package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.handler.Netty4ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Netty4Client {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) {
                    ch.pipeline().addLast(new StringEncoder())
                            .addLast(new StringDecoder())
                            .addLast(new Netty4ClientHandler());
                }
            });
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
