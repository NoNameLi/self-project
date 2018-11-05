package cn.peng.studygodpath.java8.io.nio.net;

public class Server {

    private static int DEFAULT_PORT = 12345;
    private static ServerHandle_NIO serverHandle;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandle_NIO(port);
        new Thread(serverHandle, "Server").start();
    }


    public static void main(String[] args) {
        Server.start();
    }
}
