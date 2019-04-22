package cn.peng.studygodpath.java8.io.nio.buffer;

import org.testng.annotations.Test;

import java.nio.ByteBuffer;

/**
 * NIO 中buffer 的基础
 */
public class BufferBase {

    @Test
    public void run(){

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 分配直接缓存区
        //ByteBuffer.allocateDirect(1024);
    }
}
