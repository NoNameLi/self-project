package cn.peng.studygodpath.java8.io.nio;

import cn.peng.studygodpath.java8.io.util.Calculator;
import sun.net.www.http.ChunkedInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 请求处理任务
 * 解析、计算表达式
 */
public class ServerHandle_NIO implements Runnable {

    private int port = 12345;

    private Calculator calculator = new Calculator();

    private Selector selector = null;

    private boolean status = false;

    public ServerHandle_NIO() {
        this.initServer();
    }

    public ServerHandle_NIO(int port) {
        this.port = port;
        this.initServer();
    }

    public void initServer() {
        try {
            // 创建选择器
            selector = Selector.open();
            // 创建监听通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // true 为 阻塞模式 false 非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 绑定 端口 设置连接进入队列的最大值
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 选择器注册通道 响应事件 即感兴趣的的事件发生
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 标记 服务端正常启动运行
            this.status = true;
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while (status) {
            try {
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                selector.select(1000);
                //阻塞,只有当至少一个注册的事件发生的时候才会继续.
//              selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    // 处理
                    handleAccept(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 状态为false
        if (null != selector) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        synchronized (this) {
            this.status = false;
        }
    }

    /**
     * 处理接受
     *
     * @param key
     * @throws IOException
     */
    private void handleAccept(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 设置 通道为非阻塞
            socketChannel.configureBlocking(false);
            // 注册为读
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        // 读
        if (key.isReadable()) {
            handleRead(key);
        }
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 分配1M缓存空间
        ByteBuffer bb = ByteBuffer.allocate(1024);
        // 数据从通道到 Buffer 中
        int readLength = socketChannel.read(bb);
        // 从 buffer 中获取数据
        if (readLength > 0) {
            //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
            bb.flip();
            //根据缓冲区可读字节数创建字节数组
            byte[] data = new byte[bb.remaining()];
            bb.get(data);
            float result = calculator.cal(new String(data, "UTF-8"));
            // 返回响应结果
            ChannelUtil.write(socketChannel, new StringBuilder().append(result).toString());
        } else if (readLength < 0) {
            key.cancel();
            socketChannel.close();
        }
    }
}
