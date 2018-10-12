package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

/**
 * Created by remote on 2018/4/14.
 */
public class Producer<T> {

    private Depot<T> depot = null;

    public  Producer(Depot<T> depot){
        this.depot = depot;
    }

    public void run() {

    }
}
