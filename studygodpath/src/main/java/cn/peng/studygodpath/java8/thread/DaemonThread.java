package cn.peng.studygodpath.java8.thread;

import java.util.stream.Stream;

/**
 * 守护线程
 * 主线程
 * 子线程
 */
public class DaemonThread {

    public static void main(String[] args) {
        System.out.println("主线程start。。。。。。");
        Thread children = new Thread(() -> {
            Stream.iterate(0, t -> ++t).limit(50).map(t -> "子线程:" + t).forEach(System.out::println);
        });
        Thread daemon = new Thread(() -> {
            Stream.iterate(0, t -> ++t).limit(50).map(t -> "守护线程:" + t).forEach(System.out::println);
        });

        children.start();
        daemon.setDaemon(true);
        daemon.start();

        System.out.println("主线程end。。。。。。");
    }


}
