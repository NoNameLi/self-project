package cn.peng.studygodpath.java8.thread;

import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class SleepUtil {


    public static void sleepRandom() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sleepTest() throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finish");
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " finish");
                lock.notifyAll();
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        thread1.join();
        thread.join();
    }


}
