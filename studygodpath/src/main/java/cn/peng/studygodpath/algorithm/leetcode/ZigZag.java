package cn.peng.studygodpath.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 将字符串 "PAYPALISHIRING" 写成之字形格式 使用给定的行数
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 */
public class ZigZag {

    public String convert(String s, int numRows) {
        int[] flag = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {

        }
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); j += numRows) {
            }
//                buff[i] = s.charAt(i);
        }

        return null;
    }

    @Test
    public void test(){
        int[] arr = new int[]{4,5,6,3,2,1};
        int partition = partition(arr, 1, 5);
        System.out.println(Arrays.toString(arr));
        System.out.println(partition);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int partition(int[] arr, int l, int r) {
        int val = arr[l];
        int j = l, i = l + 1;
        for (; i <= r; i++) {
            if (arr[i] < val) {
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, l, j);
        return j;
    }

}
