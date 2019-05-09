package cn.peng.studygodpath.java8.thread;

import java.util.concurrent.ThreadLocalRandom;

public class SleepUtil {


    public static void sleepRandom() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
