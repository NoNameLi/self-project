package cn.peng.studygodpath.java8.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReviewNIO {
    private static CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        Selector selector1 = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1000));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(100));

        System.out.println("服务器启动....");
        //noinspection InfiniteLoopStatement
        while (true) {
            selector.select();
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                poolExecutor.submit(() -> selectKeyHandler(selector, selectionKey));
//                selectKeyHandler(selector,selectionKey);
                keyIterator.remove();
            }
        }
    }

    public static void selectKeyHandler(Selector selector, SelectionKey selectionKey) {
        try {
            System.out.println(Thread.currentThread().getName());
            if (selectionKey.isAcceptable()) {
                SocketChannel socket = ((ServerSocketChannel) selectionKey.channel()).accept();
                socket.configureBlocking(false);
                socket.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel socket = ((SocketChannel) selectionKey.channel());
                ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                while (socket.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    System.out.print(decoder.decode(byteBuffer));
                    byteBuffer.flip();
                }
                System.out.print("\n");
                selectionKey.interestOps(SelectionKey.OP_WRITE);
            } else if (selectionKey.isWritable()) {
                SocketChannel socket = ((SocketChannel) selectionKey.channel());
                ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                byte[] bytes = "response data success code message".getBytes();
                int loop = bytes.length / byteBuffer.capacity() + 1;
                for (int i = 0; i < loop; i++) {
                    int offset = i * byteBuffer.capacity();
                    int length = loop - i == 1 ? bytes.length - i * byteBuffer.capacity() : byteBuffer.capacity();
                    byteBuffer.put(bytes, offset, length);
                    byteBuffer.flip();
                    socket.write(byteBuffer);
                    byteBuffer.flip();
                }
                selectionKey.interestOps(SelectionKey.OP_READ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
