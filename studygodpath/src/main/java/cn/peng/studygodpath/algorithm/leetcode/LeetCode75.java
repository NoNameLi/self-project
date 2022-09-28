package cn.peng.studygodpath.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 必须在不使用库的sort函数的情况下解决这个问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-colors
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * <p>
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class LeetCode75 {

    public static void main(String[] args) {
        int[] arr = {0, 1, 0};
        sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sortColors(int[] nums) {
        // 红 、蓝 指针
        int r = 0, b = nums.length - 1, i = 1;
        int rTemp = nums[r], bTemp = nums[b];// 把头尾两个空出来

        // TODO 换成一个temp 就可以了
        // 运行指针，之前 红or 白 如果碰到红 就替换一
        // 红指针 ，之前都是红
        // 蓝指针 之后都是蓝
        for (; i < b; ) {
            if (nums[i] == 0 && r < i) {// 红
                nums[r] = nums[i];
                r++;
                nums[i] = nums[r];
            } else if (nums[i] == 2) {//蓝
                nums[b] = nums[i];
                b--;
                nums[i] = nums[b];
            } else { //白 or 追上
                i++;
            }
        }
        if (rTemp < bTemp) {
            nums[r] = rTemp;
            nums[b] = bTemp;
        } else {
            nums[r] = bTemp;
            nums[b] = rTemp;
        }
    }
}
