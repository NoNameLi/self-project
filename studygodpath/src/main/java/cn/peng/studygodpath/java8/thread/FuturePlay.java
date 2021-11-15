package cn.peng.studygodpath.java8.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-11 17:42
 * QDescription:
 */
public class FuturePlay {

    public void futureCombine(){
        /**
         * 整合结果 上一阶段 和本阶段的结果
         */
    }
    @Test
    public void futureRun() throws ExecutionException, InterruptedException {
        /**
         * 无输入无输出
         */
        CompletableFuture one = CompletableFuture.supplyAsync(() -> "one");
        CompletableFuture two = one.thenRun(() -> System.out.println("then run"));
        System.out.println(two.get());
    }

    @Test
    public void futureAccpet() throws ExecutionException, InterruptedException {
        /**
         * 前一阶段的 输出 当做本阶段的输入 不输出
         */

        CompletableFuture one = CompletableFuture.supplyAsync(() -> "one");
        CompletableFuture<Void> resutl = one.thenAccept((result) -> System.out.println(result));
        System.out.println(resutl.get());

    }


    @Test
    public void futureApply() throws ExecutionException, InterruptedException {

        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> "one");
        /**
         * 前一阶段的 输出 当做本阶段的输入，并输出
         */
        CompletableFuture<String> two = one.thenApplyAsync((result) -> result + ":two");
        System.out.println(two.get()); // 输出 one:two
    }


    @Test
    public void futureTest() throws ExecutionException, InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = (runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setName("future-");
            return thread;
        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5, // 核心线程数，空闲状态也不会销毁的线程数，除非设置了允许核心线程时间超时参数
                10, // 最大运行的线程数，
                60, // 非核心线程，空闲存货的时间
                TimeUnit.SECONDS, // keep alive time 的时间单位
                queue, // 存储任务的队列， 通过execute 方法提交的任务
                threadFactory);// 创建线程的工厂方法
        Future<?> runnableFuture = poolExecutor.submit(() -> {
            System.out.println("runnable");
        });
        Future<String> callableFuture = poolExecutor.submit(() -> {
            System.out.println("callable");
            return "callable";
        });
        for (; !runnableFuture.isDone(); ) {
        }
        System.out.println("runnableFuture is completable");
        String s = callableFuture.get();
        System.out.println("callableFunture is completable,result :" + s);
    }


    @Test
    public void completableFutureWhenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {

            System.out.println("first exec:" + Thread.currentThread().getName());
            return "first Task";
        });
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<String> future = first.whenCompleteAsync((v, k) -> {
            System.out.println("when complete exec:" + v + ":" + Thread.currentThread().getName());
            if (null != k) {
                k.printStackTrace();
            }
        });
//        System.out.println("first future get:" + first.get());
//        System.out.println("when future get:" + future.get());
        System.out.println("main thread:" + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(5);
    }


    @Test
    public void CompletableFutureAllAndAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return "future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return "future2";
        });
//        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2);
        CompletableFuture<Object> any = CompletableFuture.anyOf(future1, future2);
//        System.out.println(all.get());
        System.out.println(any.get());
    }


}
