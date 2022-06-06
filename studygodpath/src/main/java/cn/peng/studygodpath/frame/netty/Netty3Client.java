package cn.peng.studygodpath.frame.netty;

import cn.peng.studygodpath.frame.netty.handler.netty3.ClientTextHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class Netty3Client {

    public static void main(String[] args) throws InterruptedException {
        ClientBootstrap bootstrap = new ClientBootstrap();
        bootstrap.setFactory(new NioClientSocketChannelFactory());
        bootstrap.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("my", new ClientTextHandler());
            return pipeline;
        });
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 1000));
        ChannelFuture future = connect.getChannel().write("hi , i am client");
        future.sync();
    }
}
