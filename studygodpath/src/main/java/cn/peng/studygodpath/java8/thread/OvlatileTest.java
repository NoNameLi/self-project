package cn.peng.studygodpath.java8.thread;

public class OvlatileTest {

    public static volatile Boolean stop = false;

    public static void main(String[] args) throws Exception{
//        test();
        test2();
    }

    public static void test2()throws Exception{
        Thread watch = new Thread(()->{
            while (!stop) {
            }
            System.out.println(Thread.currentThread() +":" + stop);
        });
        Thread change = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            stop = true;
            System.out.println("change thread is finish");
        });
        watch.start();
        change.start();
        watch.join();
    }

    public static void test() throws Exception{
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("Thread stop i=" + i);
        });
        thread.start();
        Thread.sleep(1000);
        stop = true;
        System.out.println("now, in main thread stop is: " + stop);
        thread.join();
    }


}
