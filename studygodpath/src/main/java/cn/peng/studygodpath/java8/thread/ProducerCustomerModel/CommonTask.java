package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by remote on 2018/4/14.
 * 通用任务类
 */
@Data
@Builder
public class CommonTask implements AbstrackTask {
    /**
     * 任务id
     */
    private String taskId;

    /**
     * 生产者id
     */
    private String producerId;

    /**
     * 消费者id
     */
    private String customerId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消费时间
     */
    private Date customerTime;

    /**
     * 任务完成时间
     */
    private Date finishTime;

    @Override
    public void createTask(String producterId, Date createTime) {
        this.producerId = producterId;
        this.createTime = createTime;
    }

    @Override
    public void customeTask(String customerId, Date customTime) {
        this.customerId = customerId;
        this.customerTime = customTime;
    }

    @Override
    public void overTask(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String getTaskInfo() {
//        return this.toString();
        return this.taskId;
    }
}
