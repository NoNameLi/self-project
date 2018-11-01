package cn.peng.studygodpath.java8.io.bio;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final String server_id = "127.0.0.1";
    private static final int port = 12345;

    public void send(String expression) {

        try (Socket socket = new Socket(server_id, port);
             // 输出流：向服务端发送数据
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
             // 输入流：获取服务端响应的数据
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            pw.println(expression);
            System.out.println("_表达式：" + expression);
            System.out.println("_结果为：" + br.readLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
