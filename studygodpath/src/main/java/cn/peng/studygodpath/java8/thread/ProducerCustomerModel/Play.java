package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

/**
 * Created by remote on 2018/4/14.
 */
public class Play {

    public static void main(String[] args) {
        Depot<Task> depot = new Depot<>(10);

        Producer<Task> producer = new Producer<>(depot);
        Customer<Task> customer = new Customer<>(depot);

//        for(int i = 0;i < 10; i++){
//            new Thread(producer).start();
//        }
//
//        for(int i = 0;i < 10; i++){
//            new Thread(customer).start();
//        }
    }
}
