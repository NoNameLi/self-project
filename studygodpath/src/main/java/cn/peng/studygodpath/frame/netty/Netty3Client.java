package cn.peng.studygodpath.frame.netty;

import cn.peng.studygodpath.frame.netty.entity.Request;
import cn.peng.studygodpath.frame.netty.handler.netty3.ClientResponseHandler;
import cn.peng.studygodpath.frame.netty.handler.netty3.ClientTextHandler;
import cn.peng.studygodpath.frame.netty.handler.netty3.RequestEncoder;
import cn.peng.studygodpath.frame.netty.handler.netty3.ResponseDecoder;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;

public class Netty3Client {

    /**
     * 简单文本数据处理器
     */
    private static ChannelPipelineFactory simpleTextPipelineFactory = () -> {
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("idle", new IdleStateHandler(new HashedWheelTimer(), 10, 10, 50));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new ClientTextHandler());
        return pipeline;
    };

    /**
     * 自定义数据包 request response数据处理器
     */
    private static ChannelPipelineFactory customPackagePipelineFactory = () -> {
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("idle", new IdleStateHandler(new HashedWheelTimer(), 10, 10, 50));
        pipeline.addLast("encoder", new RequestEncoder());
        pipeline.addLast("decoder", new ResponseDecoder());
        pipeline.addLast("handler", new ClientResponseHandler());
        return pipeline;
    };

    public static void main(String[] args) throws InterruptedException {
        ClientBootstrap bootstrap = new ClientBootstrap();
        bootstrap.setFactory(new NioClientSocketChannelFactory());
//        bootstrap.setPipelineFactory(simpleTextPipelineFactory);
        bootstrap.setPipelineFactory(customPackagePipelineFactory);
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 1000));
//        ChannelFuture future = connect.getChannel().write("hi server");
        ChannelFuture future = connect.getChannel().write(Request.of((short) 1, (short) 1, null));
        ChannelFuture future2 = connect.getChannel().write(Request.of((short) 1, (short) 2, null));
        future.sync();
        future2.sync();
    }
}
