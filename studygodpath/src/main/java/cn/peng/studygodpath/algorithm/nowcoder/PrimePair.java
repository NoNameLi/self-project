package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PrimePair {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int x = getNum2(n);
        System.out.println(x);
        System.out.println(n - x);
    }

    // 从中间向外，第一个就是最小的
    public static int getNum2(int num) {
        int size = num / 2;
        for (int i = size; i >= 2; i--) {
            if (isPre(i) && isPre(num - i)) {
                return i;
            }
        }
        return 0;
    }

    public static int getNum(int num) {
        int x = 0, size = num / 2, minDiff = Integer.MAX_VALUE;
        for (int i = 2; i <= size; i++) {
            if (isPre(i) && isPre(num - i)) {
                int currentDiff = i > size ? 2 * i - num : num - 2 * i;
                if (minDiff > currentDiff) {
                    minDiff = currentDiff;
                    x = i;
                }
            }
        }
        return x;
    }

    public static boolean isPre(int num) {
        double size = Math.sqrt(num);
        for (int i = 2; i <= size; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
