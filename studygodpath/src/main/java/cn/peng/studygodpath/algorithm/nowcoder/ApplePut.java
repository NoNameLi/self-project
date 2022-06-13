package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
 * 注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。
 *
 * 分两种情况
 * 1.假设有一个盘子为空，则(m,n)问题转化为将m个苹果放在n-1个盘子上，即求得(m,n-1)即可
 * 2.假设所有盘子都装有苹果，则每个盘子上至少有一个苹果，即最多剩下m-n个苹果，问题转化为将m-n个苹果放到n个盘子上即求(m-n，n)
 */
public class ApplePut {

    public static int[][] dp = new int[11][11];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mn = br.readLine().split(" ");
        int m = Integer.valueOf(mn[0]), n = Integer.valueOf(mn[1]);
        System.out.println(cal(n, m));
    }


    public static int cal(int n, int m) {
        if (n == 1 || m == 0 || m == 1) {
            if (dp[n][m] == 0) {
                dp[n][m] = 1;
            }
        } else if (m < n) {
            if (dp[n][m] == 0) {
                dp[n][m] = cal(m, m);
            }
        } else {
            if (dp[n - 1][m] == 0) {
                dp[n - 1][m] = cal(n - 1, m);
            }
            if (dp[n][m - n] == 0) {
                dp[n][m - n] = cal(n, m - n);
            }
            dp[n][m] = dp[n - 1][m] + dp[n][m - n];
        }
        return dp[n][m];
    }
}
