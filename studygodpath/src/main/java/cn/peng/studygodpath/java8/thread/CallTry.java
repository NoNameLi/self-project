package cn.peng.studygodpath.java8.thread;

import java.util.concurrent.Callable;

/**
 * Created by remote on 2017/9/29.
 */
public class CallTry implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        return 0;
    }
}
