package cn.peng.studygodpath.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * Created by remote on 2018/4/2. 首先，将待排序的数组从前向后和从后向前各取出一个元素进行对比交换，
 * 从而将待排序的数组分成两个部分，前一部分的所有元素都小于后一部分的所有元素，
 * 但前后两部分内部仍然是无序的状态。然后再将前一部分的所有元素从前向后和从后向前各取出一个元素进行对比交换，
 * 从而将前一部分的所有元素再分成两个部分，这两部分的前一部分的所有元素都小于后一部分的所有元素，依次类推，
 * 直到被分割的部分只有一个元素为止。下一步，再将后一部分的所有元素从前向后和从后向前各取出一个元素进行对比交换并分成两个部分。 这样分到最后，数组将排好序。
 * 
 * 快速排序改进： 空穴法（挖坑法） 直接交换法 单行划分法
 * 
 * 随机基准法 三数中值法
 * 
 * 进一步改进： 在小范围的数据，快速排序的效率不如插入排序，可以在长度分割到一定的长度 使用插入排序使 片段有序 这个长度一般在 10~15
 * 
 */
public class FastImporveSort implements Sort {
    Random random = new Random();

    @Override
    public void sort(Integer[] data) {
        holeFast(data, 0, data.length - 1);
    }

    public void holeFast(Integer[] data, int start, int end) {
        if (start < end) {
            randomBenchmark(data, start, end);
            // 使用挖坑法
            int midIndex = hole(data, start, end);
            holeFast(data, start, midIndex - 1);
            holeFast(data, midIndex + 1, end);
        }
    }

    public void swapFast(Integer[] data, int start, int end) {
        if (start < end) {
            threeNumMidBenchmark(data, start, end);
            // 使用直接交换法
            int midIndex = directSwap(data, start, end);
            holeFast(data, start, midIndex);
            holeFast(data, midIndex + 1, end);
        }
    }

    /**
     * 随机基准（随机一个基准，将改基准预第一个交换）
     * 
     * @param data
     * @param start
     * @param end
     */

    private void randomBenchmark(Integer[] data, int start, int end) {
        int midIndex = random.nextInt(end - start) + start;
        // 与第一个交换一下 就可以使用以第一个为基准 == 随机
        swap(data, start, midIndex);
    }

    /**
     * 三数取中 基准
     * 
     * 三数：第一个，中间一个，最后一个
     * 
     * @param data
     * @param start
     * @param end
     */
    private void threeNumMidBenchmark(Integer[] data, int start, int end) {
        int mid = (start + end) / 2;
        if (data[mid] < data[start])
            swap(data, mid, start);
        if (data[start] > data[end])
            swap(data, start, end);
        if (data[mid] > data[end])
            swap(data, mid, end);
        // 将基准值放在第一位上
        swap(data, start, mid);
    }

    /**
     * 挖坑法 内层循环的顺序不能变，除非改变基准为最后一个 内层循环的判断 必须加=，否则会有死循环
     */
    private int hole(Integer[] data, int start, int end) {
        Integer mid = data[start];
        int beginIndex = start, endIndex = end;
        for (; beginIndex < endIndex;) {
            for (; beginIndex < endIndex && data[endIndex] >= mid; endIndex--) {
            }
            data[beginIndex] = data[endIndex];
            for (; beginIndex < endIndex && data[beginIndex] <= mid; beginIndex++) {
            }
            data[endIndex] = data[beginIndex];
        }
        data[endIndex] = mid;
        return endIndex;
    }

    private int directSwap(Integer[] data, int start, int end) {
        Integer mid = data[start];
        int beginIndex = start, endIndex = end;
        for (;;) {
            do {
                endIndex--;
            } while (data[endIndex] > mid);
            do {
                beginIndex++;
            } while (data[beginIndex] < mid);
            if (beginIndex < endIndex) {
                swap(data, beginIndex, endIndex);
            } else {
                return endIndex;
            }
        }
    }

    public void test2() {
        Integer[] arr = { 1, 2 };
        swap(arr, 0, 1);
        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test() {
        Integer[] arr = getIntegerArr();
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        new FastImporveSort().sort(arr);
        new FastImporveSort().swapFast(arr3, 0, arr3.length - 1);
        new BubbleSort().sort(arr2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.equals(arr, arr2));
        System.out.println(Arrays.equals(arr, arr3));
    }

}
