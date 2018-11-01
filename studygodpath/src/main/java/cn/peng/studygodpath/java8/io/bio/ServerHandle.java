package cn.peng.studygodpath.java8.io.bio;

import cn.peng.studygodpath.java8.io.util.Calculator;

import java.io.*;
import java.net.Socket;

/**
 * 请求处理任务
 * 解析、计算表达式
 */
public class ServerHandle implements Runnable {

    private Socket socket = null;

    private Calculator calculator = new Calculator();

    public ServerHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            //输入流 可以读取客户端的数据
            String expre = null;
            float result;
            while ((expre = br.readLine()) != null) {
                result = calculator.cal(expre);
                //输出流 向客户端发送数据
                pw.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
