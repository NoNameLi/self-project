package cn.peng.studygodpath.algorithm.leetcode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 有两个长度分别为 m 和 n 的有序数组 nums1 和 num2
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 找到这两个数组的中间，总共的运行时间复杂度应该是 O(log(m+n))
 * You may assume nums1 and nums2 cannot be both empty.
 * 你可以假定 muns1 和 nums2 不会都为空
 * <p>
 * Example：
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length, total = n + m;
        int[] buff = new int[total];
        int i = 0, j = 0;
        for (; i < n && j < m; ) {
            if (nums1[i] < nums2[j]) {
                buff[i + j] = nums1[i];
                i++;
            } else {
                buff[i + j] = nums2[j];
                j++;
            }
        }
        if (i == nums1.length) {
            System.arraycopy(nums2, j, buff, i + j, m - j);
        } else {
            System.arraycopy(nums1, i, buff, i + j, n - i);
        }
        if (total % 2 == 0) {
            int ii = (total - 1) / 2;
            return (buff[ii] + buff[ii + 1]) / 2.0;
        } else {
            return buff[total / 2];
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length, total = n + m, a = total / 2, v1 = 0,v2;
        if (total % 2 == 0) {
            a = a - 1;
        }
        int i = 0, j = 0;
        for (; i < n && j < m; ) {
            if (nums1[i] < nums2[j]) {
                if (i + j == a) v1 = nums1[i];
                if (i + j == a + 1) v2 = nums1[i];
                i++;
            } else {
                if (i + j == a) v1 = nums1[i];
                if (i + j == a + 1) v2 = nums1[i];
                j++;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] n = {1, 3}, m = {2};
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(n, m));
    }
}
