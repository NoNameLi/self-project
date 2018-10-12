package cn.peng.studygodpath.java8.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by remote on 2017/9/29.
 */
public class RunThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> future = new FutureTask<Integer>(new CallTry());
        new Thread(future).start();
        Thread.sleep(1000 *5);
        System.out.println(future.get());
    }


}
