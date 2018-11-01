package cn.peng.studygodpath.java8.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {

    private final int DEFAULT_SERVER_PORT = 12345;

    private int serverPort;

    // 阿里规范 强制 使用 ThreadPoolExecutor 替换 Executors
    ExecutorService threadPool = Executors.newFixedThreadPool(10);
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public Server() {
        this.serverPort = this.DEFAULT_SERVER_PORT;
        this.init();
    }

    public Server(int port) {
        this.serverPort = port;
        this.init();
    }

    private void init() {

        try (ServerSocket ss = new ServerSocket(this.serverPort)) {
            System.out.println("服务器已启动，端口号：" + this.serverPort);
            while (true) {
                Socket accept = ss.accept();// 会阻塞，直到有请求进来
                //new Thread(new ServerHandle(accept)).start();
                // 改进线程池
//                threadPool.execute(new ServerHandle(accept));
                threadPoolExecutor.execute(new ServerHandle(accept));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
