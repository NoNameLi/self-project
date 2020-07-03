package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import java.util.Date;

public interface TaskFactory<T> {
    T create(String producerId, Date createTime);
}
