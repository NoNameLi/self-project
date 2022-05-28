package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.handler.PrintHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Netty3Demo {

    public static void main(String[] args) {
        ServerBootstrap server = new ServerBootstrap();
        ThreadPoolExecutor bossPoll = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(0));
        ThreadPoolExecutor workerPoll = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        server.setFactory(new NioServerSocketChannelFactory(bossPoll, workerPoll));
        server.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast("handler", new PrintHandler());
            return pipeline;
        });
        server.bind(new InetSocketAddress(1000));
    }
}
