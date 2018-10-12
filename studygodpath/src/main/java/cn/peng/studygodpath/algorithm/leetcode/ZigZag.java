package cn.peng.studygodpath.algorithm.leetcode;

/**
 * 将字符串 "PAYPALISHIRING" 写成之字形格式 使用给定的行数
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 *
 */
public class ZigZag {

    public String convert(String s, int numRows) {
        int[] flag = new int[s.length()];
        for(int i = 0; i < s.length();i++){

        }
        for(int i = 0 ;i< numRows; i++){
            for(int j = i; j < s.length();j+= numRows){}
//                buff[i] = s.charAt(i);
        }

        return null;
    }


}
