package cn.peng.studygodpath.java8.collection;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-13 17:54
 * QDescription:
 */
public class TestQueue {
    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(1000);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    queue.offer(new Object());
                    queue.remove();
                }
            }).start();
        }
        while (true) {
            System.out.println("begin scan, i still alive");
            queue.stream()
                    .filter(o -> o == null)
                    .findFirst()
                    .isPresent();
            Thread.sleep(100);
            System.out.println("finish scan, i still alive");
        }
    }
}