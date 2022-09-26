package cn.peng.studygodpath.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode27 {

    public static void main(String[] args) {
        int[] arr = {0, 2, 2, 1, 5, 5, 7, 5};

        System.out.println(solution(arr, 5));
        System.out.println(Arrays.toString(arr));
    }

    public static int solution(int[] arr, int target) {
        int length = arr.length;
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i] == target) {
                copy(arr, i, length - 1);
                length--;
            }
        }
        return length;
    }

    public static void copy(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            arr[i] = arr[i + 1];
        }
    }
}
