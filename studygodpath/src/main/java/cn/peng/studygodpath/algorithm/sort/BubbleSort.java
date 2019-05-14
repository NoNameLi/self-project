package cn.peng.studygodpath.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void test2() {
        int[] ints = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(this.bubblePractice(ints)));
    }

    public int[] bubblePractice(int[] arr) {
        int swap = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }

        }
        return arr;
    }


}
