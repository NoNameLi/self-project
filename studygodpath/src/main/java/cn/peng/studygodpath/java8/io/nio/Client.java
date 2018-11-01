package cn.peng.studygodpath.java8.io.nio;

public class Client {

    private static int DEFAULT_PORT = 12345;
    private static String DEFAULT_IP = "127.0.0.1";

    private static ClientHandle_NIO clientHandle;

    public static void start() {
        start(DEFAULT_IP, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null)
            clientHandle.stop();
        clientHandle = new ClientHandle_NIO(ip, port);
        new Thread(clientHandle, "Client").start();
    }
}
