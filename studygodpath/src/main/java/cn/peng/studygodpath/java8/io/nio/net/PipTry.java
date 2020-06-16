package cn.peng.studygodpath.java8.io.nio.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by remote on 2018/1/12.
 */
public class PipTry {


    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();
            final Pipe.SinkChannel psic = pipe.sink();
            final Pipe.SourceChannel psoc = pipe.source();

            Thread thread = new Thread() {
                public void run() {
                    try {
                        psic.write(ByteBuffer.wrap("Hello Pope".getBytes()));//"utf-16BE"

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread readThread = new Thread() {
                public void run() {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    try {
                        psoc.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
