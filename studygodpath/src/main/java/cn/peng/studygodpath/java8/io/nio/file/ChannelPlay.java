package cn.peng.studygodpath.java8.io.nio.file;

import cn.peng.studygodpath.java8.io.bio.Server;
import org.testng.annotations.Test;

import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelPlay {

    public void getChannelByObjectClassGetChannel() throws Exception {
        // 本地IO
        FileInputStream fis = new FileInputStream(new File(""));
        fis.getChannel();
        FileOutputStream fos = new FileOutputStream(new File(""));
        fos.getChannel();
        // 网络IO
        Socket socket = new Socket();
        socket.getChannel();
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.getChannel();
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.getChannel();
    }

    public void getChannelByOpen() throws IOException {
        FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        SocketChannel.open();
        ServerSocketChannel.open();
        DatagramChannel.open();
        Files.newByteChannel(Paths.get(""),StandardOpenOption.READ);
    }


    @Test
    public void channleCopyFile() throws Exception {
        FileInputStream fis = new FileInputStream("E:\\迅雷下载\\1.jpg");
        FileOutputStream fos = new FileOutputStream("E:\\迅雷下载\\1_1.jpg");

        FileChannel isChannle = fis.getChannel();
        FileChannel outChannle = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (isChannle.read(buffer) != -1) {
            buffer.flip();
            outChannle.write(buffer);
            buffer.clear();
        }
        outChannle.close();
        isChannle.close();
        fos.close();
        fis.close();
    }

    @Test
    public void steamCopyFile() throws Exception {
        FileInputStream fis = new FileInputStream("E:\\迅雷下载\\1.jpg");
        FileOutputStream fos = new FileOutputStream("E:\\迅雷下载\\1_2.jpg");
        byte[] buffer = new byte[1024];

        while (fis.read(buffer) != -1) {
            fos.write(buffer);
        }
        fos.close();
        fis.close();
    }

    // mmap 实现零拷贝，利用虚拟内存，将用户缓冲器 和 内核缓冲区 映射到一个虚拟内存上，减少 内核缓冲区到用户缓冲区的一次拷贝
    public void directCopyFile() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get("D:/1.rmvb"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/2.rmvb"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        // 获取直接缓存区
        MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, outChannel.size());

        byte[] buffer = new byte[inMappedBuffer.limit()];
        inMappedBuffer.get(buffer);
        outMapperBuffer.put(buffer);
//        outChannel.write(inMappedBuffer);
        inChannel.close();
        outChannel.close();
    }

    // sendFile 实现零拷贝，
    public void channelCopyFile() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:/1.rmvb"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/2.rmvb"), StandardOpenOption.WRITE, StandardOpenOption.READ);

        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }

}
