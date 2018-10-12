package cn.peng.studygodpath.algorithm.sort;

import org.junit.Test;

/**
 * Created by remote on 2018/4/1. 冒泡排序
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        Integer mid = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    mid = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = mid;
                }
            }
        }
    }

    @Test
    public void test() {
        new BubbleSort().execSort();
    }
}
