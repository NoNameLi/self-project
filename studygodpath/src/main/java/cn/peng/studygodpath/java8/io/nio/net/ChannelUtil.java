package cn.peng.studygodpath.java8.io.nio.net;

import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelUtil {
    private ChannelUtil() {
    }

    /**
     * 写
     *
     * @param socketChannel
     * @param data
     * @throws IOException
     */
    public static void write(SocketChannel socketChannel, String data) throws IOException {
        byte[] bdata = data.getBytes();
        ChannelUtil.write(socketChannel, bdata);
    }

    public static void write(SocketChannel socketChannel, byte[] data) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
        byteBuffer.put(data);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

}
