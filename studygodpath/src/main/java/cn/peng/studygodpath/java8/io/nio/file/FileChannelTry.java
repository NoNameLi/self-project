package cn.peng.studygodpath.java8.io.nio.file;

import cn.peng.studygodpath.FilePathUtil;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by remote on 2018/1/12.
 */
public class FileChannelTry {

    @Test
    public void write2() {
        try {
            FileOutputStream fos = new FileOutputStream(new File("out2.txt"));
            byte[] data = "NIO测试写".getBytes(Charset.forName("utf8"));
            long time = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                fos.write(data);
            }
            System.out.println(System.currentTimeMillis() - time);
            FileChannel channel = fos.getChannel();
            ByteBuffer bb = Charset.forName("utf8").encode("NIO测试写");
            time = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                channel.write(bb);
            }
            System.out.println(System.currentTimeMillis() - time);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void write() {
        String filename = "out.txt";
        FileOutputStream fos = null;
        try {
            File file = new File(filename);
            fos = new FileOutputStream(file);
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode("你好你好你好你好你好");
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
            System.out.println("初始化容量和limit：" + src.capacity() + ","
                    + src.limit());
            int length = 0;

            while ((length = channel.write(src)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
                System.out.println("写入长度:" + length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void read() {
        try {
            String base_path = FilePathUtil.getProjectPath();
            RandomAccessFile afile = new RandomAccessFile(base_path + File.separator + "io.txt", "rw");
            ByteBuffer buf = ByteBuffer.allocate(48);
            FileChannel inchannel = afile.getChannel();

            int bytesRead = inchannel.read(buf);
            while (bytesRead != -1) {
                buf.flip();
                StringBuilder sb = new StringBuilder();
                while (buf.hasRemaining()) {
                    sb.append(buf.getInt());
                }
                System.out.print(sb.toString());
                buf.clear();
                bytesRead = inchannel.read(buf);
            }
            afile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


