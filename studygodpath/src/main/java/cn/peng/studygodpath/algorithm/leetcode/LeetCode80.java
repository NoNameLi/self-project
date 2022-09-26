package cn.peng.studygodpath.algorithm.leetcode;

import java.util.Arrays;

/**
 * 原题链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/description/
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class LeetCode80 {


    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(solution(arr));
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {1, 1, 1, 2, 2, 3};
        System.out.println(solution(arr2));
        System.out.println(Arrays.toString(arr2));
    }

    public static int solution(int[] arr) {
        int length = arr.length;
        int current = length - 1, i = current;
        for (; i >= 0; i--) {
            // 0 x x x
            if (arr[i] == arr[current]) {
                continue;
            } else {
                length = length - overCopy(arr, i, current);
                current = i;
            }
        }
        // 数组头重复处理
        length = length - overCopy(arr, i, current);
        return length;
    }

    public static int overCopy(int[] arr, int start, int end) {
        int step = end - start - 2;
        if (step > 0) {
            copy(arr, start + 1, step);
            return step;
        }
        return 0;
    }

    public static void copy(int[] arr, int start, int step) {
        for (int i = start; i < arr.length - step; i++) {
            arr[i] = arr[i + step];
        }
    }
}
