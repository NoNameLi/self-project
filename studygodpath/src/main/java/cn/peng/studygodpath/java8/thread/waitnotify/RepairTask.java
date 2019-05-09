package cn.peng.studygodpath.java8.thread.waitnotify;

import cn.peng.studygodpath.java8.thread.SleepUtil;

public class RepairTask implements Runnable {

    private Washroom washroom;

    public RepairTask(Washroom washroom) {
        this.washroom = washroom;
    }

    @Override
    public void run() {
        synchronized (washroom.getLock()) {
            System.out.println("维修工获取厕所占有权 ，始维修。。。");
            SleepUtil.sleepRandom();
            washroom.setAvaliable(true);
            washroom.getLock().notifyAll();
            System.out.println("维修工释放厕所占有权");
        }
    }
}
