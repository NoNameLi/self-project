package cn.peng.studygodpath.algorithm.leetcode;

import java.util.HashSet;

/**
 * Longest Substring Without Repeating Characters
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", which the length is 3.
 */
public class LongetSubsttring {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int size = s.length();
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            for (; j < size; j++) {
                if (contants(s, i, j, s.charAt(j))) {
                    break;
                }
            }
            max = j - i > max ? j - i : max;
        }
        return max;
    }

    public boolean contants(String s, int beging, int end, char c) {
        for (int i = beging; i < end; i++) {
            if (s.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }


    public int lengthOfLongestSubstring2(String s) {
        HashSet<Character> freq = new HashSet<>();
        if (s.length() < 2)
            return s.length();
        int i = 0, j = 0;
        int maxLen = 0;
        while (j < s.length()) {
            maxLen = Math.max(maxLen, j - i);
            if (!freq.contains(s.charAt(j))) {
                freq.add(s.charAt(j));
                j++;
                continue;
            }
            freq.remove(s.charAt(i));
            i++;
        }

        return Math.max(maxLen, j - i);
    }


    public static void main(String[] args) {

        //System.out.println(new LongetSubsttring().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LongetSubsttring().lengthOfLongestSubstring("dvdf"));

    }

}
