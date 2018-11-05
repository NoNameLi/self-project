package cn.peng.studygodpath.java8.io.nio.net;

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
        ByteBuffer byteBuffer = ByteBuffer.allocate(bdata.length);
        byteBuffer.put(bdata);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }
}
