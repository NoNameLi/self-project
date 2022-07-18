package cn.peng.studygodpath.algorithm;

/**
 * 文本相似度计算
 */
public class TextSimilarity {

    public static void main(String[] args) {
//        System.out.println(minEditDistance());
    }

    public static float similarity(String a, String b) {
        if (a == null && b == null) {
            return 1f;
        }
        if (a == null || b == null) {
            return 0F;
        }
        int editDistance = minEditDistance(a, b);
        return 1 - ((float) editDistance / Math.max(a.length(), b.length()));
    }


    /**
     * 编辑距离
     * f(i,j) 代表a[0...i] b[0...j]的最小编辑距离
     * 1. 已知 f(i-1,j), 那么 a[0,i] 删除 a[i],即 a[0...i-1] 则 f(i,j) = f(i-1,j) + 1
     * 2. 已知 f(i,j-1), 那么 b[0,j] 删除 b[j],即 b[0...j-1] 则 f(i,j) = f(i,j-1) + 1
     * 3. 已知 f(i-1,j-1), 如果 a[i] == b[j] 则 f(i,j) = f(i -1, j -1), a[i] != b[j],则 f(i,j) = f(i-1,j-1) + 1 (替换操作)
     */
    public static int minEditDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                    dp[i][j] = dp[i][j] + 1;
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
