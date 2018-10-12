package cn.peng.studygodpath.algorithm.leetcode;

/**
 * Longest Palindromic Substring
 * 最长的回文子串
 * <p>
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Example：
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 */
public class LongestPalindromicSubstring {

    int max, sIndex, eIndex;

    public String longestPalindrome(String s) {
        int sl = s.length();
        if (sl <= 1) {
            return s;
        }
        for (int i = sl - 1; i >= 0; i--) {
            for (int j = 0, k = j + i; k < sl; j++, k++) {
                if (isPalindromic(s, j, k)) {
                    return new String(s.toCharArray(), j, k - j + 1);
                }
            }
        }
        return s;
    }

    public boolean isPalindromic(String s) {
        return isPalindromic(s, 0, s.length());
    }

    public String longestPalindrome2(String s) {
        if (s.length() <= 1) {
            return s;
        }
        /**
         * 分成 奇回文 和 偶回文
         * 从中间 两边循环扩展 找到最大的回文 并记录 两边的下标
         */
        for (int i = 0; i < s.length(); i++) {
            getMaxPalindromw(s, i, i);
            getMaxPalindromw(s, i, i + 1);
        }
        return s.substring(sIndex, eIndex + 1);
    }

    public void getMaxPalindromw(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        //此时 l r 分别多减 多加 一位
        if (max < r - l - 1) {
            max = r - l - 1;
            sIndex = l + 1;
            eIndex = r - 1;
        }


    }


    public boolean isPalindromic(String s, int m, int n) {
        for (int i = m, j = n; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome(""));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome("ac"));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abb"));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));

        System.out.println(new LongestPalindromicSubstring().longestPalindrome2(""));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome2("ac"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome2("abb"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome2("babad"));

    }


}
