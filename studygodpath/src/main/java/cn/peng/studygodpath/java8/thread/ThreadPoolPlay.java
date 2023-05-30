package cn.peng.studygodpath.java8.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用
 */
public class ThreadPoolPlay {

    public void createThreadPool() {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 8, 8, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
    }


}
