package cn.peng.studygodpath.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * <p>
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * <p>
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * <p>
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode26 {

    public static int removeDuplicates(int[] arr) {
        // 0 1 1 1 2
        int duplicate = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] == arr[i + 1]) {
                int j = i;
                if (i != 0) {
                    for (j = j - 1; j >= 0; j--) {// 往前判断
                        if (arr[j] != arr[i]) {
                            break;
                        }
                    }
                    j++;
                }
                // 拷贝
                int diff = i - j + 1;
                copyArr(arr, i, diff);
                duplicate += diff;
                i = j;
            }
        }
        return arr.length - duplicate;
    }

    public static void copyArr(int[] arr, int start, int diff) {
        for (int i = start; i < arr.length - diff; i++) {
            arr[i] = arr[i + diff];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3};
        int length = removeDuplicates(arr);
        System.out.println(length);
        System.out.println(Arrays.toString(arr));
    }

}
