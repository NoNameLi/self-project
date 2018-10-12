package cn.peng.studygodpath.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * Created by remote on 2018/4/6. 归并排序是将两个已经排好序的序列合并成一个序列的操作。
 * 归并排序的过程是这样的。首先，将待排序的数组中的元素从中间分为前后两部分。然后再将前一部分继续分成两部份，后一部分也继续分成两部分，
 * 依次类推，直到单个元素为止。最后两两按序合并，直到整个数组合并成一个有序数组为止。 要注意判断边界
 */
public class MergeSort implements Sort {

    @Override
    public void sort(Integer[] data) {

    }

    /**
     * 两个序列合并
     * 
     * @param data
     *            数据
     * @param start
     *            开始
     * @param mid
     *            中间
     * @param end
     *            结束
     */
    public void merge(Integer[] data, int start, int mid, int end) {
        Integer[] temp = new Integer[data.length];
        int i = start, j = mid + 1, t = start;
        for (; i <= mid && j <= end; t++) {
            if (data[i] < data[j]) {
                temp[t] = data[i++];
            } else {
                temp[t] = data[j++];
            }
        }
        // 把剩余的放到缓存中
        if (i <= mid) {// 前面的序列还有剩余
            for (; i <= mid; t++, i++) {
                temp[t] = data[i];
            }
        }
        if (j <= end) {// 前面的序列还有剩余
            for (; j <= end; t++, j++) {
                temp[t] = data[j];
            }
        }
        for (int m = start; m <= end; m++) {
            data[m] = temp[m];
        }
    }

    private void mergeSort(Integer[] data, int start, int end) {
        int mid;
        if (start < end) {
            mid = (start + end) / 2;
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }

    @Test
    public void test() {
        new FastSort().execSort();
        Integer[] data = { 8, 4, 6, 3, 1, 7, 5, 2 };
        new MergeSort().mergeSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

}
