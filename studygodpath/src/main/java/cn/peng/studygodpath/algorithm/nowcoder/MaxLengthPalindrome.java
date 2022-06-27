package cn.peng.studygodpath.algorithm.nowcoder;


import java.io.IOException;

/**
 * 找最长回文
 */
public class MaxLengthPalindrome {


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String text = br.readLine();
        long start = System.nanoTime();
        method1("ABBA");
        method1("ABBBA");
        method1("12HHHHA");
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        method2("ABBA");
        method2("ABBBA");
        method2("12HHHHA");
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        method3("ABBA");
        method3("ABBBA");
        method3("12HHHHA");
        System.out.println(System.nanoTime() - start);
    }


    public static void method3(String text) {
        char[] chars = text.toCharArray();
        // dp[i][j] 代表   i 到j 对应的字符串是否是回文
        // 状态转移方程， chars[i] == chars[j] dp[i][j] = dp[i+1][j-1]
        boolean[][] dp = new boolean[chars.length][chars.length];
        int max = 0;
        for (int r = 0, s = chars.length; r < s; r++) {
            for (int l = 0; l <= r; l++) {
                if (l == r || (chars[l] == chars[r] && (r - l < 2 || dp[l + 1][r - 1]))) {
                    // 单个字符
                    dp[l][r] = true;
                    max = Math.max(max, r - l + 1);
                } else {
                    dp[l][r] = false;
                }
            }
        }
        System.out.println(max);
    }

    public static void method2(String text) {
        char[] chars = text.toCharArray();
        // dp[i] 代表 前i个字符 最大回文数
        // dp[i] = max(dp[i-1],以i为结尾的回文长度)
        int[] dp = new int[chars.length];
        for (int i = 0, j = chars.length; i < j; i++) {
            if (i == 0) {
                dp[i] = 1;
            } else {
                dp[i] = Math.max(dp[i - 1], b(chars, i));
            }
        }
        System.out.println(dp[chars.length - 1]);
    }

    public static int b(char[] arr, int right) {
        for (int i = 0; i <= right; i++) {
            int end = right;
            for (int j = i; j <= right; j++, end--) {
                if (j == end || j > end) {
                    return right - i + 1;
                } else if (arr[j] != arr[end]) {
                    break;
                }
            }
        }
        return 1;
    }


    // 从中间向两边，两种情况 奇偶
    public static void method1(String text) {
        char[] chars = text.toCharArray();
        int s = 0;
        for (int i = 0, j = chars.length; i < j; i++) {
            s = Math.max(s, Math.max(a(chars, i, i), a(chars, i, i + 1)));
        }
        System.out.println(s);
    }

    public static int a(char[] arr, int left, int right) {
        int i = left, j = right;
        for (; i >= 0 && j < arr.length; i--, j++) {
            if (arr[i] != arr[j]) {
                break;
            }
        }
        return j - i - 1;
    }


}
