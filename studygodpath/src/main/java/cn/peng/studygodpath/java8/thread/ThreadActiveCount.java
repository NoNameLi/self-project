package cn.peng.studygodpath.java8.thread;

/**
 * Thread.activeCount() 》 1
 * 在 cmd or eclipse 中 会返回1 在 idea 中返回2 因为idea 在运行时会开启一个监控线程
 */
public class ThreadActiveCount {
    private static int rate = 0;
    
    public static void add(){
        rate++;
    }

    public static void main(String[] args) {
        Thread.currentThread().getThreadGroup().list();
        Thread[] threads =  new Thread[10];
        for (int i = 0;i < 10;i++){
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    add();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(rate);
    }
    
}
