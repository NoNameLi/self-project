package cn.peng.studygodpath.java8.io.nio.buffer;

import org.testng.annotations.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * NIO 中buffer 的基础
 */
public class BufferBase {

    @Test
    public void run() {
        String str = "abcdef";
        // 分配 缓冲区 建立在vm的内存中
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("------------allocate-------------");
        printBufferSize(byteBuffer);
        // 分配直接缓存区 缓冲区建立在 物理内存中，可以提供效率
        //ByteBuffer.allocateDirect(1024);
        byteBuffer.put(str.getBytes());
        System.out.println("------------put-------------");
        printBufferSize(byteBuffer);

        byteBuffer.flip();// 重置 position 设置 limit 可以理解为 从写转读\
        System.out.println("------------flip-------------");
        printBufferSize(byteBuffer);

        System.out.println("------------get-------------");
        byte[] buffer = new byte[byteBuffer.limit()];
        byteBuffer.get(buffer);
        printBufferSize(byteBuffer);
        System.out.println("get content:" + new String(buffer));

        System.out.println("------------rewind-------------");
        byteBuffer.rewind();
        printBufferSize(byteBuffer);

        System.out.println("------------clear-------------");
        byteBuffer.clear();
        printBufferSize(byteBuffer);
    }

    private void printBufferSize(Buffer buffer) {
        System.out.println("capacity size: " + buffer.capacity());
        System.out.println("limit size: " + buffer.limit());
        System.out.println("position size:" + buffer.position());

    }
}
