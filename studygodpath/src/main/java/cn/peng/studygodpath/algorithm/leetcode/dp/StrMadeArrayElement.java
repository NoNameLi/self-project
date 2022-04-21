package cn.peng.studygodpath.algorithm.leetcode.dp;

import org.testng.collections.Lists;

import java.util.List;

public class StrMadeArrayElement {

    public static boolean checkStr(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 0; i < length; i++) {
            for (int j = i; dp[i] && j < length; j++) {
                if (wordDict.contains(s.substring(i, j + 1))) {
                    dp[j + 1] = true;
                }
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        System.out.println(checkStr("xiaohongshu", Lists.newArrayList("xi", "xiao", "hong", "shu")));
    }


}
