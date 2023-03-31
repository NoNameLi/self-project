package cn.peng.studygodpath.java8.thread;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OvlatileTest {

    public static volatile Boolean stop = false;

    private static volatile int num = 0;

    @Test
    public void testVolatileVisibility() throws InterruptedException {
        Thread writeThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                num++;
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread readThread = new Thread(() -> {
            while (num < 100 - 1) {
            }
            System.out.println(Thread.currentThread().getName() + " read:" + num);
        });
        writeThread.start();
        readThread.start();
        readThread.join();
    }


    @Test
    public void testVolatileIntAdd() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                num = num + 1;
            }
        };

        List<Thread> threadList = Lists.newArrayListWithCapacity(20);
        for (int i = 0; i < 20; i++) {
            threadList.add(new Thread(runnable));
        }
        for (int i = 0; i < 20; i++) {
            threadList.get(i).start();
        }
        for (int i = 0; i < 20; i++) {
            threadList.get(i).join();
        }
        Assert.assertEquals(num, 1000 * 20);
    }

    public static void main(String[] args) throws Exception {
//        test();
        test2();
    }

    public static void test2() throws Exception {
        Thread watch = new Thread(() -> {
            while (!stop) {
            }
            System.out.println(Thread.currentThread() + ":" + stop);
        });
        Thread change = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            stop = true;
            System.out.println("change thread is finish");
        });
        watch.start();
        change.start();
        watch.join();
    }

    public static void test() throws Exception {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("Thread stop i=" + i);
        });
        thread.start();
        Thread.sleep(1000);
        stop = true;
        System.out.println("now, in main thread stop is: " + stop);
        thread.join();
    }


}
