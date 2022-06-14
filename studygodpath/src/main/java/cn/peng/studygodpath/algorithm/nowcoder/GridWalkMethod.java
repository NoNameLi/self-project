package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GridWalkMethod {

    public static void main(String[] args) throws Exception {
//        method2();
        method4();
    }

    public static void method1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int n = Integer.parseInt(arr[0]) + 1, m = Integer.parseInt(arr[1]) + 1;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = 1;
        }
        // f(n,m) = f(n-1,m) + f(n,m-1)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }

    //不管怎么走，总要从左上到右下，那么要向下走n次，向右走m次，总共也只走m+n次，
    // 那么方法数就是从这m+n次中选出m次向右走的方案，即Cm+n  m
    // ，那么最终方案数就是：
    //(m+1)(m+2)(m+3)....(m+n) / {(m+1)(m+2)(m+3)....(m+n)}{n!}
    public static void method2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int n = Integer.parseInt(arr[0]), m = Integer.parseInt(arr[1]);
        int a = 1, b = 1;

        for (int i = 1; i <= n; i++) {
            a *= m + i;
            b *= i;
        }
        System.out.println(a / b);
    }

    public static void method3() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] man = br.readLine().split(" ");
        int m = Integer.parseInt(br.readLine());
        String[] ticket = br.readLine().split(" ");
        Map<String, Integer> map = new HashMap<>(n);
        int invalid = 0;
        for (int i = 0, j = man.length; i < j; i++) {
            map.put(man[i], 0);
        }

        for (int i = 0, j = ticket.length; i < j; i++) {
            if (map.containsKey(ticket[i])) {
                map.put(ticket[i], map.get(ticket[i]) + 1);
            } else {
                invalid++;
            }
        }
        map.entrySet().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

    public static void method4() throws Exception {
        char[] chars = "1**3".toCharArray();
        StringBuilder sb = new StringBuilder();

        int start = -1;
        for (int i = 0, j = chars.length; i < j; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                if (start == -1)
                    start = i;
            } else {
                if (start >= 0) {
                    sb.append("*").append(chars, start, i - start).append("*");
                    start = -1;
                }
                sb.append(chars[i]);
            }
        }
        if (start >= 0) {
            sb.append("*").append(chars, start, chars.length - start).append("*");
            start = 0;
        }
        System.out.println(sb);
    }

}
