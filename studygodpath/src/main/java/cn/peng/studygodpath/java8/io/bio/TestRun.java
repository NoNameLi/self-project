package cn.peng.studygodpath.java8.io.bio;

import java.util.Random;

/**
 * 测试运行
 */
public class TestRun {


    public static void main(String[] args) throws InterruptedException {
        /**服务端*/
        new Thread(() -> {
            new Server();
        }).start();

        Thread.sleep(1000);


        /** 客户端*/
        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(()-> {
            Client client = new Client();
//            String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
//            client.send(expression);
            while (true) {
                //随机产生算术表达式
                String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                client.send(expression);
                try {
                    Thread.currentThread().sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
