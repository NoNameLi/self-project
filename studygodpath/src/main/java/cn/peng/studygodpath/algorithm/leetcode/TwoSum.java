package cn.peng.studygodpath.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * 给一个int 类型的数组，两个数之和等于一个特定的值返回他们的索引
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * 你可以 假定 每个输入都有一个明确的结果，并且 你不能使用同一个元素两次
 * 
 * eg: Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] = 2 +
 * 7 = 9, return [0, 1].
 * 
 * 
 * @author remote
 *
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] indens = new int[2];
        for (int i = 0; i < nums.length; i++) {
            indens[0] = i;
            int t = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (t == nums[j]) {
                    indens[1] = j;
                    break;
                }
            }
            if (indens[0] < indens[1]) {
                break;
            }
        }
        return indens;
    }

    public int[] twoSumV2(int[] nums, int target) {
        int[] indens = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        int key;
        for (int i = 0; i < nums.length; i++) {
            indens[0] = i;
            key = target - nums[i];
            if (map.containsKey(key)) {
                indens[1] = map.get(key);
                return indens;
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
//        System.out.println(Arrays.toString(new TwoSum().twoSum(nums, 23)));
//        System.out.println(Arrays.toString(new TwoSum().twoSumV2(nums, 9)));
        
        
    }

}
