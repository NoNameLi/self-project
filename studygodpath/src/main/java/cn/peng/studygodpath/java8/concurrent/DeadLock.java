package cn.peng.studygodpath.java8.concurrent;

/**
 * @Author: Administrator
 * @CreateTime:2022-08-19 17:10
 * QDescription:
 */
public class DeadLock {

    private static Object lockA = new Object();
    private static Object lockB = new Object();


    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "获取锁:A");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "获取锁:B");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "获取锁:B");
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "获取锁:A");
                }
            }
        }).start();
    }



}
