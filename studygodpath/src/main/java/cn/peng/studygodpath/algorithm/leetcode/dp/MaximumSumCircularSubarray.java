package cn.peng.studygodpath.algorithm.leetcode.dp;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-15 11:06
 * QDescription: 918. 环形子数组的最大和
 */
public class MaximumSumCircularSubarray {

    /**
     * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
     * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
     * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
     * <p>
     * 输入：nums = [1,-2,3,-2]
     * 输出：3
     * 解释：从子数组 [3] 得到最大和 3
     * <p>
     * 输入：nums = [5,-3,5]
     * 输出：10
     * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
     */
    public static void main(String[] args) {
        int[] nums = new int[]{9, -4, -7, 9};
        System.out.println(new MaximumSumCircularSubarray().maxSubarraySumCircular(nums));
    }

    public int maxSubarraySumCircular(int[] nums) {
        int preMax = 0, max = nums[0], preMin = 0, min = nums[0], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            preMax = nums[i] + Math.max(preMax, 0);
            max = Math.max(preMax, max);

            preMin = nums[i] + Math.min(preMin, 0);
            min = Math.min(preMin, min);
            sum += nums[i];
        }
        return Math.max(max, sum == min ? max : sum - min);
    }

}
