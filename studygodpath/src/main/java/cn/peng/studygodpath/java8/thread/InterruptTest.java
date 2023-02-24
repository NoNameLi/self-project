package cn.peng.studygodpath.java8.thread;

import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程中断测试
 */
public class InterruptTest {

    private static void doSomething() {
        int i = 0;
        while (i <= 10000) { // 耗时操作
            i++;
            RandomUtil.randomNumbers(100);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (InterruptTest.class) {
                System.out.println("synchronized 开始执行。。。。");
                doSomething();
                System.out.println("synchronized 结束执行。。。。");
            }
        });
        thread.start();
        System.out.println("synchronized 线程中断");
        thread.interrupt();
        thread.join();

        ReentrantLock lock = new ReentrantLock();
        Thread interruptTread = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                doSomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        interruptTread.start();
        interruptTread.interrupt();
        System.out.println("reentrantLock lockInterruptibly 线程中断");
    }


}
