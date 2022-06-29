package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 给定两个只包含小写字母的字符串，计算两个字符串的最大公共子串的长度。
 * <p>
 * 注：子串的定义指一个字符串删掉其部分前缀和后缀（也可以不删）后形成的字符串。
 * 数据范围：字符串长度：1≤s≤150
 * 输入描述：
 * 输入两个只包含小写字母的字符串
 * <p>
 * 输出描述：
 * 输出一个整数，代表最大公共子串的长度
 */
public class MaxSubstring {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        String min, max;
        if (a.length() >= b.length()) {
            min = b;
            max = a;
        } else {
            min = a;
            max = b;
        }
        int size = min.length();

        int[] dp = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    dp[j] = Math.max(max.contains(String.valueOf(min.charAt(i))) ? 1 : 0, dp[j]);
                } else {
                    dp[j] = Math.max(max.contains(min.substring(j - dp[j - 1], j + 1)) ? dp[j - 1] + 1 : dp[j - 1], dp[j]);
                }
            }
        }

        System.out.println(dp[size - 1]);

    }
}
