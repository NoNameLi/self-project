package cn.peng.studygodpath.java8.thread.waitnotify;

public class ShitTask implements Runnable {

    private String name;
    private Washroom washroom;

    public ShitTask(String name, Washroom washroom) {
        this.name = name;
        this.washroom = washroom;
    }


    @Override
    public void run() {
        synchronized (washroom.getLock()) {
            System.out.println(name + "，获取厕所占用权");
            while (!washroom.isAvaliable()) {
                try {
                    System.out.println(name + "，厕所不可用，释放占有权");
                    washroom.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + "使用完厕所，释放占用权");
        }
    }
}
