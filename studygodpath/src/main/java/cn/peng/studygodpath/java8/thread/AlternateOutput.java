package cn.peng.studygodpath.java8.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-19 13:59
 * QDescription:线程交替输出
 */
public class AlternateOutput {

    //用两个线程，一个输出字母，一个输出数字，交替输出A1B2C3D4…Z26
    public static void main(String[] args) throws Exception {
//        methodOne();
//        methodTwo();
//        methodThree();
        methodFour();
    }

    enum Run {T1, T2}

    private static volatile Run run = Run.T1;

    static class Res {
        public String name;
        public String sex;
    }

    public static void methodFour() {
        Res res = new Res();
        new Thread(() -> {
            int count = 0;
            while (true) {
                if (run == Run.T1) {
                    if (count == 0) {
                        res.name = "小红";
                        res.sex = "女";
                    } else {
                        res.name = "小军";
                        res.sex = "男";
                    }
                    count = (count + 1) % 2;
                    run = Run.T2;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (run == Run.T2) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(res.name + ":" + res.sex);
                    run = Run.T1;
                }
            }
        }).start();
    }


    public static void methodThree() throws Exception {
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (run == Run.T2) {
                }
                System.out.print((char) (65 + i));
                run = Run.T2;
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (run == Run.T1) {
                }
                System.out.print(1 + i);
                run = Run.T1;
            }
        }).start();
    }


    public static void methodTwo() throws Exception {
        CyclicBarrier cb = new CyclicBarrier(2);
        Thread outChar = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print((char) (65 + i));
                try {
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread outInt = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(1 + i);
                try {
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        outChar.start();
        outInt.start();
        outChar.join();
        outInt.join();
    }


    public static void methodOne() throws InterruptedException {
        StringBuilder lock = new StringBuilder();
        Thread outChar = new Thread(new CharIntRunnable(true, lock));
        Thread outInt = new Thread(new CharIntRunnable(false, lock));
        outChar.start();
        outInt.start();
        outChar.join();
        outInt.join();
        System.out.println(lock.toString());
    }

    static class CharIntRunnable implements Runnable {

        private StringBuilder lock = null;
        private boolean isChar;

        public CharIntRunnable(boolean isChar, StringBuilder lock) {
            this.isChar = isChar;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 26; i++) {
                synchronized (lock) {
                    lock.notify();
                    if (isChar) {
                        lock.append((char) (65 + i));
                    } else {
                        lock.append(1 + i);
                    }
                    if (i < 25) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

}
