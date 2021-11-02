package cn.peng.studygodpath.algorithm.leetcode;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 22:24
 * QDescription:
 */
public class LeetCode283 {

    @Test
    public void testPerformance() {
        int size = 10;
        int[] arr1 = new int[size], arr2 = new int[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = RandomUtils.nextInt(0, 10);
            arr2[i] = arr2[i];
        }
        long start = System.nanoTime();
        removeZero(arr1);
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        removeZeroOp(arr2);
        System.out.println(System.nanoTime() - start);
    }

    @Test
    public void test() {
        int[] arr = new int[]{0, 5, 5, 1, 0, 3, 0, 12};
        removeZeroOp(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void swap(int arr[], int s, int t) {
        int tmp = arr[s];
        arr[s] = arr[t];
        arr[t] = tmp;
    }
    /**
     * 移动零。给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 示例:输入: [0,1,0,3,12]。输出: [1,3,12,0,0]。说明:必须在原数组上操作，不能拷贝额外的数组。尽量减少操作次数。力扣283。
     *
     * @param arr
     */
    public void removeZeroOp(int[] arr) {
        int to = 0, e = arr.length;
        for (int i = 0; i < e; i++) {
            if (arr[i] != 0) {
                swap(arr, i, to++);
            }

        }
    }

    public void removeZero(int[] arr) {// 有优化的空间 见上
        int s1 = 0, s2 = s1, e = arr.length - 1, tmp;

        while (s1 <= e) {
            while (s1 <= e) {
                if (arr[s1] == 0)
                    break;
                s1++;
            }
            s2 = s1 + 1;
            while (s2 <= e) {
                if (arr[s2] != 0)
                    break;
                s2++;
            }
            if (s2 <= e) {
                swap(arr, s1, s2);
            } else {
                break;
            }
        }
    }

}
