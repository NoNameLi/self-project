package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import java.util.Date;

/**
 * Created by remote on 2018/4/14.
 * 生产者
 */
public class Producer<T extends AbstrackTask>/* implements Runnable */ {
    //id 使用线程的id
    private String producerId = String.valueOf(Thread.currentThread().getId());

    private Depot<? super T> depot = null;

    public Producer(Depot<? super T> depot) {
        this.depot = depot;
    }

    //    @Override
    public void run() {
        // 两秒生产一个
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            AbstrackTask task = CommonTask.builder().producerId(producerId).createTime(new Date()).build();
//            depot.add(task);
        }
    }
}
