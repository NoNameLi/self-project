package cn.peng.studygodpath.algorithm.leetcode.dp;

import org.testng.collections.Lists;

import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class LeetCode139StrMadeArrayElement {

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
