package cn.peng.studygodpath.algorithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

/**
 * Created by remote on 2018/4/2.
 */
public interface Sort {
    /** 数组生产长度 */
    static final int array_length = 10;
    /** 数组数据随机生成器 */
    static final Random rand = new Random();

    void sort(Integer[] data);

    default void swap(Integer[] arr, int i, int j) {
        if (i != j) {// 位运算交换 不能同值交换，会有0的情况出现
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    default void execSort() {
        Integer[] arr = getIntegerArr();
        System.out.println(Arrays.toString(arr));
        this.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    default Integer[] getIntegerArr() {
        List<Integer> arr = Lists.newArrayList();
        for (int i = 0; i < array_length; i++) {
            arr.add(rand.nextInt(100));
        }
        return (Integer[]) arr.toArray(new Integer[0]);
    }

}
