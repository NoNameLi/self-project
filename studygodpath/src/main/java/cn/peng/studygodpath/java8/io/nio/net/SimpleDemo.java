package cn.peng.studygodpath.java8.io.nio.net;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 通道的读写 一定要写对 ，buffer的 读写模式也要写对 有问题 首先冲着两方面查找
 */
public class SimpleDemo {

    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        Charset charset = Charset.forName("UTF-8");
//        sChannel.configureBlocking(false);
        ByteBuffer byteBuffer = charset.encode("Hello Client, I am Servlet");
//        FileChannel fChannel = FileChannel.open(Paths.get(""), StandardOpenOption.READ);
//        while (fChannel.write(byteBuffer) != -1) {
//            byteBuffer.flip();
//            sChannel.write(byteBuffer);
//            byteBuffer.clear();
//        }
        sChannel.write(byteBuffer);
        sChannel.shutdownOutput();
        byteBuffer.flip();
        while (sChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            System.out.println(charset.decode(byteBuffer).toString());
            byteBuffer.clear();
        }

        sChannel.close();

    }

    @Test
    public void servlet() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
        Charset charset = Charset.forName("UTF-8");
        SocketChannel socketChannel = ssChannel.accept();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            CharBuffer charBuffer = charset.decode(byteBuffer);
            System.out.println(charBuffer.toString());
            byteBuffer.clear();
        }
        socketChannel.write(charset.encode("Hello Servlet ,I am Client"));
        socketChannel.close();
        ssChannel.close();

    }

    @Test
    public void client_nio() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.configureBlocking(false);
        Charset charset = Charset.forName("UTF-8");
        Scanner scanner = new Scanner(System.in);
        ByteBuffer resultBuffer = ByteBuffer.allocate(1024);
        while (scanner.hasNext()) {
            ByteBuffer buffer = charset.encode(scanner.next());
            socketChannel.write(buffer);
//            socketChannel.shutdownOutput();
            while (socketChannel.read(resultBuffer) > 0) {
                resultBuffer.flip();
                System.out.println("发送成功，收到结果：" + charset.decode(resultBuffer).toString());
                resultBuffer.clear();
            }
        }
        socketChannel.close();
    }


    @Test
    public void serlet_nio() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Charset charset = Charset.forName("UTF-8");

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {//接受
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isConnectable()) {

                } else if (selectionKey.isReadable()) {// 读就绪
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        System.out.println("接受到信息：" + charset.decode(byteBuffer));
                    }
                    socketChannel.write(charset.encode("success"));
                } else if (selectionKey.isWritable()) {

                } else {

                }
            }
        }
    }


    @Test
    public void udp_client() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            java.lang.String text = scanner.next();
            byteBuffer.put((new Date().toString() + "\n" + text).getBytes());
            byteBuffer.flip();
            datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 9999));
            byteBuffer.clear();
        }
        datagramChannel.close();
    }

    @Test
    public void udp_servlet() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
        datagramChannel.configureBlocking(false);
        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    DatagramChannel dc = (DatagramChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    dc.receive(byteBuffer);
                    System.out.println(new java.lang.String(byteBuffer.array()));
                    byteBuffer.clear();
                }
                iterator.remove();
            }
        }

    }
}
