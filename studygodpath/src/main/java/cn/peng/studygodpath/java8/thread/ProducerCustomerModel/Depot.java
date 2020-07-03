package cn.peng.studygodpath.java8.thread.ProducerCustomerModel;

import com.beust.jcommander.internal.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remote on 2018/4/14.
 * 仓库
 */
public class Depot<T> {
    // 可以使用队列

    private int maxStock = 10;

    private List<T> data = null;

    public Depot(int maxStock) {
        this.maxStock = maxStock;
        data = Lists.newArrayList(maxStock);
    }

    public void add(T t) {
        data.add(t);
    }

    public T get() {
        return data.remove(1);
    }

    public static void main(String[] args) {
        Depot<Integer> test = new Depot<>(5);
        test.add(2);
    }

}
