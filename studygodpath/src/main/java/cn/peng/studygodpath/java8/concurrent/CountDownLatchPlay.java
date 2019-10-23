package cn.peng.studygodpath.java8.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 让一个 或多个线程 等待其他一系列线程结束 才继续执行
 */
public class CountDownLatchPlay {

    CountDownLatch coutDownLatch = new CountDownLatch(5);


    @Test
    public void testDemo() {

        new Thread(() -> {
            try {
                coutDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("boss 开始开会");
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("员工到场");
                coutDownLatch.countDown();
            }).start();
        }

    }


}
