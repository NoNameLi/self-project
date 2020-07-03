package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

/**
 * Created by remote on 2018/4/14.
 */
public class Play {

    public static void main(String[] args) {
        Depot<CommonTask> depot = new Depot<>(10);

        Producer<CommonTask> producer = new Producer<>(depot);
        Customer<CommonTask> customer = new Customer<>(depot);

//        for(int i = 0;i < 10; i++){
//            new Thread(producer).start();
//        }
//
//        for(int i = 0;i < 10; i++){
//            new Thread(customer).start();
//        }
    }
}
