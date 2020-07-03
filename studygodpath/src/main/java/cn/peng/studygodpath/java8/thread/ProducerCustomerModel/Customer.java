package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import java.util.Date;

/**
 * Created by remote on 2018/4/14.
 * 消费者
 */
public class Customer<T extends AbstrackTask> implements Runnable {

    private Depot<T> depot = null;

    public Customer(Depot<T> depot) {
        this.depot = depot;
    }

    @Override
    public void run() {
        while (true) {
            // 消费者不停的消费
            AbstrackTask task = depot.get();
            task.customeTask(String.valueOf(Thread.currentThread().getId()), new Date());
            System.out.println(Thread.currentThread().getId() + ":" + task.getTaskInfo());
        }
    }
}
