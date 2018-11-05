package cn.peng.studygodpath.java8.io.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ClientHandle_NIO implements Runnable {

    private String ip;
    private int port;

    private boolean status = false;

    private Selector selector = null;

    private SocketChannel socketChannel = null;


    public ClientHandle_NIO(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.initClient();
    }

    public void initClient() {
        try {
            selector = Selector.open();

            socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);
            this.status = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        synchronized (this) {
            this.status = false;
        }
    }

    @Override
    public void run() {
        try {
            boolean connectFlag = socketChannel.connect(new InetSocketAddress(this.ip, this.port));
            if (!connectFlag) {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
            while (status) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    // 处理
                    handleAccept(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //selector关闭后会自动释放里面管理的资源
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 处理接受
     *
     * @param key
     * @throws IOException
     */
    private void handleAccept(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if(socketChannel.isConnectionPending()){
                    socketChannel.finishConnect();
                    System.out.println("client is finish connect");
                    this.send("1+2*6");
                }
            }
            if (key.isReadable()) {
                this.handleRead(key);
            }
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
            System.out.println("客户端收到消息：" + new String(data));
        } else if (readLength < 0) {
            key.cancel();
            socketChannel.close();
        }
    }


    public void send(String context) {
        try {
            socketChannel.register(selector, SelectionKey.OP_READ);
            ChannelUtil.write(socketChannel, context);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
