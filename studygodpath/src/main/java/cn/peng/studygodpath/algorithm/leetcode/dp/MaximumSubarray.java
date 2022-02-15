package cn.peng.studygodpath.algorithm.leetcode.dp;

/**
 * @Author: Administrator
 * @CreateTime:2022-02-15 09:32
 * QDescription: 53. 最大子数组和
 */
public class MaximumSubarray {

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 7};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray2(nums));
    }

    /**
     * 动态规划思想
     */
    public static int maxSubArray(int[] nums) {
        int pre = 0, max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }


    /**
     * 分治思想
     */
    public static int maxSubArray2(int[] nums) {
        return test(nums, 0, nums.length - 1).mSum;
    }

    public static Status test(int[] nums, int start, int end) {
        if (start == end) {
            return new Status(nums[start], nums[start], nums[start], nums[start]);
        } else {
            int min = (start + end) / 2;
            // 合并
            return merge(test(nums, start, min), test(nums, min + 1, end));
        }
    }

    public static Status merge(Status a, Status b) {
        int iSum = a.iSum + b.iSum;
        int lSum = Math.max(a.lSum, a.iSum + b.lSum);
        int rSum = Math.max(b.rSum, b.iSum + a.rSum);
        int mSum = Math.max(Math.max(a.mSum, b.mSum), a.rSum + b.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }


    public static class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;// 以左为端点的最大和
            this.rSum = rSum;// 以右为端点的最大和
            this.mSum = mSum;// 子序列最大和
            this.iSum = iSum;// 所有和
        }
    }


}
