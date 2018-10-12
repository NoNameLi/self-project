package cn.peng.studygodpath.algorithm.sort;

import org.junit.Test;

/**
 * Created by remote on 2018/4/2.
 * 
 * 插入排序 首先认为第一个为有序
 * 
 * 下一个跟前面的依次比较，小于则后移大于这赋值
 * 
 */
public class InsertSort implements Sort {

    @Override
    public void sort(Integer[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = 0;
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[i] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    @Test
    public void test() {
        new InsertSort().execSort();
    }

}
