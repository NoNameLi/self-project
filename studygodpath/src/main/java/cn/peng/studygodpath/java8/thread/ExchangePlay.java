package cn.peng.studygodpath.java8.thread;

import java.util.concurrent.Exchanger;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-10 15:21
 * QDescription: 线程数据交换
 */
public class ExchangePlay {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        for (int i = 1; i <= 10; i++) {
            Integer data = i;
            new Thread(() -> {
                try {
                    Object exchange = exchanger.exchange(data);
                    System.out.println(Thread.currentThread().getName() + "-" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Java技术栈" + i).start();
        }
    }
}
