package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

/**
 * Created by remote on 2018/4/14.
 */
public class Customer<T> {

    private Depot<T> depot = null;

    public  Customer(Depot<T> depot){
        this.depot = depot;
    }


}
