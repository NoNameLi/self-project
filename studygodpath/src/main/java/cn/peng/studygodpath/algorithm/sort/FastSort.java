package cn.peng.studygodpath.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * Created by remote on 2018/4/2.
 * 首先，将待排序的数组从前向后和从后向前各取出一个元素进行对比交换，
 * 从而将待排序的数组分成两个部分，前一部分的所有元素都小于后一部分的所有元素，
 * 但前后两部分内部仍然是无序的状态。然后再将前一部分的所有元素从前向后和从后向前各取出一个元素进行对比交换，
 * 从而将前一部分的所有元素再分成两个部分，这两部分的前一部分的所有元素都小于后一部分的所有元素，依次类推，
 * 直到被分割的部分只有一个元素为止。下一步，再将后一部分的所有元素从前向后和从后向前各取出一个元素进行对比交换并分成两个部分。
 * 这样分到最后，数组将排好序。
 */
public class FastSort implements Sort {
    @Override
    public void sort(Integer[] data) {
        sort(data, 0, data.length - 1);
    }

    private void sort(Integer[] data, int start, int end) {
        if(start >= end){
            return;
        }
        int partition = partition(data, start, end);
        sort(data, start ,partition - 1);
        sort(data, partition + 1 , end);
    }
    private static int partition(Integer[] array,int lo,int hi){
        int key = array[lo];
        while(lo < hi){
            while( lo < hi && array[hi] >= key){
                hi --;
            }
            array[lo] = array[hi];
            while (lo < hi && array[lo] <= key){
                lo ++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }


    @Test
    public void test() {
//        execSort(new FastSort());
        Integer[] data = {5,2,8,6,3,4,7,11};
        System.out.println(Arrays.toString(data));
        new FastSort().sort(data,0,data.length - 1);
        System.out.println(Arrays.toString(data));
    }
}
