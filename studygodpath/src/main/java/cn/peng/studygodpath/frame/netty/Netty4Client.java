package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.entity.Request;
import cn.peng.studygodpath.frame.netty.handler.netty4.ClientPackageHandler;
import cn.peng.studygodpath.frame.netty.handler.netty4.ClientTextHandler;
import cn.peng.studygodpath.frame.netty.handler.netty4.RequestEncoder;
import cn.peng.studygodpath.frame.netty.handler.netty4.ResponseDecoder;
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
                    .addLast(new StringEncoder()).addLast(new StringDecoder()).addLast(new ClientTextHandler());
        }
    };

    static ChannelInitializer customPackageChannelInitializer = new ChannelInitializer<NioSocketChannel>() {
        @Override
        protected void initChannel(NioSocketChannel ch) {// pipeline handler 顺序
            ch.pipeline().addLast(new IdleStateHandler(5, 5, 10))
                    .addLast(new RequestEncoder()).addLast(new ResponseDecoder()).addLast(new ClientPackageHandler());
        }
    };


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(loopGroup).channel(NioSocketChannel.class)
//                    .handler(simpleTextChannelInitializer)
                    .handler(customPackageChannelInitializer)
            ;
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 1000).sync();
//            channelFuture.channel().writeAndFlush("hello server! I am client");
            channelFuture.channel().writeAndFlush(Request.of((short) 1, (short) 1, null));
            channelFuture.channel().writeAndFlush(Request.of((short) 1, (short) 2, null));
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
