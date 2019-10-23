package cn.peng.studygodpath.java8.concurrent;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环屏障，一组线程互相等待直到所有的线程到达一个共有的屏障点，然后可以做某些操作，等待线程被换醒
 */
public class CycliBarrierPlay {

    private final static Logger logger = LoggerFactory.getLogger(CycliBarrierPlay.class);

    public void create() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CyclicBarrier cyclicBarrierDo = new CyclicBarrier(2, () -> {
            // 全部达到屏障做操作
        });
    }

    @Test
    public void play() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2);
        logger.info("barrier.getParties()获取开启屏障的方数：" + barrier.getParties());
        logger.info("通过barrier.getNumberWaiting()获取正在等待的线程数：初始---" + barrier.getNumberWaiting());
        System.out.println();
        new Thread(() -> {
            logger.info("添加第1个等待线程---" + Thread.currentThread().getName());
            currentThreadBarrier(barrier);
            logger.info(Thread.currentThread().getName() + "is terminated");
        }).start();
        TimeUnit.MILLISECONDS.sleep(10);
        logger.info("通过barrier.getNumberWaiting()获取正在等待的线程数：一个线程屏障等待后---" + barrier.getNumberWaiting());
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println();
        new Thread(() -> {
            logger.info("添加第2个等待线程---" + Thread.currentThread().getName());
            currentThreadBarrier(barrier);
            logger.info(Thread.currentThread().getName() + "is terminated");
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println();
        logger.info("通过barrier.getNumberWaiting()获取正在等待的线程数：屏障打开待后---" + barrier.getNumberWaiting());
        new Thread(() -> {
            logger.info("屏障打开后，再有线程到达屏障等待：" + Thread.currentThread().getName());
            currentThreadBarrier(barrier);
            logger.info(Thread.currentThread().getName() + "is terminated");
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println();
    }

    @Test
    public void barrierReset() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        cyclicBarrier.reset();
        logger.info("初始的cyclicBarrier reset");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                currentThreadBarrier(cyclicBarrier);
            });
        }
        cyclicBarrier.reset();
        logger.info("屏障打开后 reset");
        TimeUnit.MILLISECONDS.sleep(100);
        executorService.submit(() -> {
            currentThreadBarrier(cyclicBarrier);
        });
        logger.info("有线程到达屏障 屏障还未打开 reset 等待线程会抛出BrokenBarrierException");
        cyclicBarrier.reset();
        executorService.shutdown();
    }

    /**
     * 模拟场景
     * 多线程分组计算
     */
    public static void main(String[] args) {
        int size = 50000;
        int sum = 0;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandomUtils.nextInt(0, 1000);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        logger.info("单线程计算用时：{},结果：{}", System.currentTimeMillis() - start, sum);

        final int[] sums = {0};
        int[] groupResult = new int[5];
        long finalStart = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            for (int i = 0; i < 5; i++) {
                sums[0] += groupResult[i];
            }
            logger.info("多线程计算用时：{},结果：{}", System.currentTimeMillis() - finalStart, sums[0]);
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.submit(() -> {
                for (int j = finalI * 10000; j < (finalI + 1) * 10000; j++) {
                    groupResult[finalI] += arr[j];
                }
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    private void currentThreadBarrier(CyclicBarrier barrier) {
        try {
            barrier.await();
            logger.info(Thread.currentThread().getName() + "is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
