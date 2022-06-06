package cn.peng.studygodpath.frame.netty;


import cn.peng.studygodpath.frame.netty.handler.netty3.RequestDecoder;
import cn.peng.studygodpath.frame.netty.handler.netty3.ResponseEncoder;
import cn.peng.studygodpath.frame.netty.handler.netty3.ServerRequestHandler;
import cn.peng.studygodpath.frame.netty.handler.netty3.ServerTextHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Netty3Server {

    /**
     * 简单文本数据处理器
     */
    private static ChannelPipelineFactory simpleTextPipelineFactory = () -> {
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("idle", new IdleStateHandler(new HashedWheelTimer(), 10, 10, 50));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new ServerTextHandler());
        return pipeline;
    };

    /**
     * 自定义数据包 request response数据处理器
     */
    private static ChannelPipelineFactory customPackagePipelineFactory = () -> {
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("idle", new IdleStateHandler(new HashedWheelTimer(), 10, 10, 50));
        pipeline.addLast("decoder", new RequestDecoder());
        pipeline.addLast("encoder", new ResponseEncoder());
        pipeline.addLast("handler", new ServerRequestHandler());
        return pipeline;
    };

    public static void main(String[] args) {
        ServerBootstrap server = new ServerBootstrap();
        ThreadPoolExecutor bossPoll = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        ThreadPoolExecutor workerPoll = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        server.setFactory(new NioServerSocketChannelFactory(bossPoll, workerPoll));
//        server.setPipelineFactory(simpleTextPipelineFactory);
        server.setPipelineFactory(customPackagePipelineFactory);
        server.setOption("backlog", 2048);
        server.setOption("child.keepAlive", true);
        server.setOption("child.tcpNoDelay", true);
        server.bind(new InetSocketAddress(1000));
        server.bind(new InetSocketAddress(2000));
        System.out.println("server run.....");
    }


}
