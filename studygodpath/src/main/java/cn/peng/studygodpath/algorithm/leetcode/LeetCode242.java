package cn.peng.studygodpath.algorithm.leetcode;

import org.junit.Test;

import java.util.Objects;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 22:01
 * QDescription:
 */
public class LeetCode242 {


    @Test
    public void test() {
        System.out.println(isAnagram("aabb", "ab"));
    }

    public boolean isAnagram(String s, String t) {
        /**
         * 有效的字母异位词。给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
         * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。s 和 t 仅包含小写字母
         */
        if (Objects.equals(s, t)) {
            return true;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCount = new int[256];
        for (char c : s.toCharArray()) {
            charCount[c]++;
        }
        for (char c : t.toCharArray()) {
            if (--charCount[c] < 0) {
                return false;
            }
        }
        return true;

    }


}
