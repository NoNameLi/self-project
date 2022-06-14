package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-14 16:13
 * QDescription:
 */
public class LongestPalindromic {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        System.out.println(longestPalindrome3(text));
    }

    /**
     * 动态规划，
     * f{s,j} 表示从s到j 字符串是否是回文
     * <p>
     * Text(s) == Text(j)
     *      f{s,j} = f{s+1,j-1} & true
     * Text(s) != Text(j)
     *      f{s,j} = Max(f{s-1,j},f{s,j-1})
     */
    public static int longestPalindrome3(String text) {
        int length = text.length(), maxLen = -1;
        boolean[][] dp = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (text.charAt(i) == text.charAt(j)) {
                    if (i == j || j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                }
            }
        }
        return maxLen;
    }

}
