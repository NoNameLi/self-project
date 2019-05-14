package cn.peng.studygodpath.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by remote on 2018/4/2. 选择排序是一种简单直观的排序方法。
 * 选择排序的过程是这样的，首先，在待排序的数组中找到一个最小的数组元素，将该最小数组元素与数组的第一个元素进行交换，这样交换之后，
 * 数组的第一个元素就变成了数组元素中的最小值。然后，再在待排序数组的剩余元素中找到一个最小的数组元素，将该最小数组元素与数组的第
 * 二个元素进行交换，这样交换之后，数组的第二个元素就变成了数组元素中的第二小的值。依次类推，直至数组按顺序排好为止。
 */
public class SelectSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        Integer temp;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    @Test
    public void test() {
        new SelectSort().execSort();
        int[] ints = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(this.selectPractice(ints)));
    }

    public int[] selectPractice(int[] arr) {
        int swap = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap = arr[i];
                    arr[i] = arr[j];
                    arr[j] = swap;
                }
            }
        }
        return arr;
    }
}
