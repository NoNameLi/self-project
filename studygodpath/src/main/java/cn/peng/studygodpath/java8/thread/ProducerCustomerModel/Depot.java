package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import com.beust.jcommander.internal.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remote on 2018/4/14.
 */
public class Depot<T> {
    // 可以使用队列

    private int maxStock = 10;

    private List<T> data = null;

    public Depot(int maxStock){
        this.maxStock = maxStock;
        data = Lists.newArrayList(maxStock);
    }

    public void add(T task){
        data.add(task);
    }

    public T get(){
        return  data.remove(1);
    }


}
