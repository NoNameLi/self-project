package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import lombok.Data;

import java.util.Date;

/**
 * Created by remote on 2018/4/14.
 */
@Data
public class Task {
    /**任务id*/
    private String taskId;

    /**生产者id*/
    private String producerId;

    /**消费者id*/
    private String customerId;

    /**创建时间*/
    private Date createTime;

    /**消费时间*/
    private Date customerTime;

    /**任务完成时间*/
    private Date finishTime;
}
