package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import java.util.Date;

/**
 * 抽象goods 应该更好
 */
public interface AbstrackTask {

    void createTask(String producterId, Date createTime);

    void customeTask(String customerId, Date customTime);

    void overTask(Date finishTime);

    String getTaskInfo();
}
