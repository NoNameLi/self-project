package cn.peng.studygodpath.frame.netty;

import cn.peng.studygodpath.frame.netty.handler.Netty4ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Netty4ClientPoll {

    private static List<Channel> connectList = new ArrayList<>();

    private static AtomicInteger index = new AtomicInteger(0);

    private static Bootstrap bootstrap = null;

    private static AtomicBoolean staus = new AtomicBoolean(Boolean.FALSE);

    public static void main(String[] args) throws InterruptedException {

        bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class).handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringDecoder()).addLast(new StringEncoder()).addLast(new Netty4ClientHandler());
            }
        });
        for (int i = 0; i < 10; i++) {
            connectList.add(bootstrap.connect("192.168.0.105", 1000).sync().channel());
        }
        staus.compareAndSet(Boolean.FALSE, Boolean.TRUE);
    }


    public static Channel getConnect() throws Exception {
        int i = index.getAndIncrement() % connectList.size();
        Channel channel = connectList.get(i);
        if (!channel.isActive()) {
            // 重新连接 == 创建新对象
            reconnect(i, channel);
            if (connectList.size() == i + 1) {
                throw new Exception("poll no available connect");
            }
            return getConnect();
        }
        return channel;
    }

    private static void reconnect(int index, Channel channel) throws InterruptedException {
        if (!channel.isActive()) {
            synchronized (channel) {
                if (!channel.isActive()) {
                    connectList.set(index, bootstrap.connect("192.168.0.105", 1000).sync().channel());
                }
            }
        }
    }
}
