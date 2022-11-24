package cn.peng.studygodpath.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode215 {

    public static void main(String[] args) {
        int[] test = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int[] test = {1, 2};
        LeetCode215 leetCode215 = new LeetCode215();
        System.out.println(leetCode215.findKthLargest(test, 4));
        System.out.println(Arrays.toString(test));
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        // 快排 每次会确定中间的位置，中间的位置正好等于倒数第k 即返回，位置比k 小，重新处理右子集，比k大 处理左
        //快排
        // 找一个数，从 首找比这个数大的，从 尾找比这个数小的 交换，处理左右部分


        return nums[nums.length - k];
    }

    /**
     * 快排改，确定中间值的位置，判断是否是第k大的位置
     * 是，返回
     * 不是，判断大小
     */
    public void fastSortX(int[] nums, int l, int r, int k) {


    }

    public void fastSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int midIndex = (start + end) / 2;
        int mid = nums[midIndex];
        int i = start, j = end;

        while (i < j) {
            while (i < j && nums[i] <= mid) {
                i++;
            }
            while (i < j && nums[j] >= mid) {
                j--;
            }
            if (i < j) {
                // 交换
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (midIndex > i) {
            nums[midIndex] = nums[i];
            nums[i] = mid;
        }
        fastSort(nums, start, i - 1);
        fastSort(nums, i + 1, end);
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x));
        return 0;
    }
}
