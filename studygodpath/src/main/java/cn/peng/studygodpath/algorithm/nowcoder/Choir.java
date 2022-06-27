package cn.peng.studygodpath.algorithm.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * N 位同学站成一排，音乐老师要请最少的同学出列，使得剩下的 K 位同学排成合唱队形。
 * <p>
 * 设K位同学从左到右依次编号为 1，2…，K ，他们的身高分别为T_1,T_2,…,T_KT
 * <p>
 * 若存在i(1≤i≤K) 使得T_1<T_2<......<T_{i-1}<T_iT 且 T_i>T_{i+1}>......>T_K
 * Ti >Ti+1 > TK，则称这KK名同学排成了合唱队形。
 * 通俗来说，能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
 * 例子：
 * 123 124 125 123 121 是一个合唱队形
 * 123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
 * 123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
 * <p>
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * <p>
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 * <p>
 * 数据范围： 1≤n≤3000
 * <p>
 * 输入描述：
 * 用例两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开
 * <p>
 * 输出描述：
 * 最少需要几位同学出列
 */
public class Choir {

    /**
     * 类似01背包
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int[] dp1 = new int[n];
            Arrays.fill(dp1, 1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp1[i] = Math.max(dp1[i], dp1[j] + 1);
                    }
                }
            }

            int[] dp2 = new int[n];
            Arrays.fill(dp2, 1);
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] < arr[i]) {
                        dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                    }
                }
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, dp1[i] + dp2[i] - 1);
            }
            System.out.println(n - max);
        }
    }

}
