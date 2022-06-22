package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 主件	附件
 * 电脑	打印机，扫描仪
 * 书柜	图书
 * 书桌	台灯，文具
 * 工作椅	无
 * 如果要买归类为附件的物品，必须先买该附件所属的主件，且每件物品只能购买一次。
 * 每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
 * 王强查到了每件物品的价格（都是 10 元的整数倍），而他只有 N 元的预算。除此之外，他给每件物品规定了一个重要度，用整数 1 ~ 5 表示。他希望在花费不超过 N 元的前提下，使自己的满意度达到最大。
 * 满意度是指所购买的每件物品的价格与重要度的乘积的总和，假设设第ii件物品的价格为v[i]v[i]，重要度为w[i]w[i]，共选中了kk件物品，编号依次为j_1,j_2,...,j_kj
 * <p>
 * 则满意度为：v[j_1]*w[j_1]+v[j_2]*w[j_2]+ … +v[j_k]*w[j_k]v[j]。（其中 * 为乘号）
 * 请你帮助王强计算可获得的最大的满意度。
 * <p>
 * 输入描述：
 * 输入的第 1 行，为两个正整数N，m，用一个空格隔开：
 * （其中 N （ N<32000 ）表示总钱数， m （m <60 ）为可购买的物品的个数。）
 * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
 * <p>
 * 输出描述：
 * 输出一个正整数，为张强可以获得的最大的满意度。
 * <p>
 * 1000 5
 * 800 2 0
 * 400 5 1
 * 300 5 1
 * 400 3 0
 * 500 2 0
 */
public class ShoppingList {

    public static void main(String[] args) throws IOException {

    }

    public static void method1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int money = Integer.parseInt(s[0]);
        int n = Integer.parseInt(s[1]);
        int[] price = new int[n + 1], value = new int[n + 1], belong = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] goods = br.readLine().split(" ");
            price[i] = Integer.parseInt(goods[0]);
            value[i] = price[i] * Integer.parseInt(goods[1]);
            belong[i] = Integer.parseInt(goods[2]);
        }
        int[][] dp = new int[n + 1][money + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= money; j++) {
                if (belong[i] != 0) continue;
                int f1 = 0, f2 = 0;
                for (int m = 1; m <= n; m++) {
                    if (belong[m] == i) {
                        if (f1 == 0) {
                            f1 = m;
                        } else {
                            f2 = m;
                        }
                    }
                }
                // 不要此主品
                dp[i][j] = dp[i - 1][j];
                // 要主
                if (j >= price[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - price[i]] + value[i]);
                }
                // 要主 第一个附件
                int t1 = price[i] + price[f1];
                if (f1 > 0 && j >= t1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t1] + value[i] + value[f1]);
                }
                // 要主 第二个附件
                int t2 = price[i] + price[f2];
                if (f2 > 0 && j >= t2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t2] + value[i] + value[f2]);
                }
                // 要主 两个附件
                int t3 = t1 + price[f2];
                if (f1 > 0 && f2 > 0 && j >= t3) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t3] + value[i] + value[f1] + value[f2]);
                }
            }
        }
        System.out.println(dp[n][money]);
    }

    public static void method2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int money = Integer.parseInt(s[0]);
        int n = Integer.parseInt(s[1]);
        int[] price = new int[n + 1], value = new int[n + 1], belong = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] goods = br.readLine().split(" ");
            price[i] = Integer.parseInt(goods[0]);
            value[i] = price[i] * Integer.parseInt(goods[1]);
            belong[i] = Integer.parseInt(goods[2]);
        }
        int[] dp = new int[money + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = money; j >= 0; j--) {
                if (belong[i] != 0) continue;
                int f1 = 0, f2 = 0;
                for (int m = 1; m <= n; m++) {
                    if (belong[m] == i) {
                        if (f1 == 0) {
                            f1 = m;
                        } else {
                            f2 = m;
                        }
                    }
                }
                // 不要此主品
                dp[j] = dp[j];
                // 要主
                if (j >= price[i]) {
                    dp[j] = Math.max(dp[j], dp[j - price[i]] + value[i]);
                }
                // 要主 第一个附件
                int t1 = price[i] + price[f1];
                if (f1 > 0 && j >= t1) {
                    dp[j] = Math.max(dp[j], dp[j - t1] + value[i] + value[f1]);
                }
                // 要主 第二个附件
                int t2 = price[i] + price[f2];
                if (f2 > 0 && j >= t2) {
                    dp[j] = Math.max(dp[j], dp[j - t2] + value[i] + value[f2]);
                }
                // 要主 两个附件
                int t3 = t1 + price[f2];
                if (f1 > 0 && f2 > 0 && j >= t3) {
                    dp[j] = Math.max(dp[j], dp[j - t3] + value[i] + value[f1] + value[f2]);
                }
            }
        }
        System.out.println(dp[money]);
    }

}
