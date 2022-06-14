package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 完全数（Perfect number），又称完美数或完备数，是一些特殊的自然数。
 * <p>
 * 它所有的真因子（即除了自身以外的约数）的和（即因子函数），恰好等于它本身。
 * <p>
 * 例如：28，它有约数1、2、4、7、14、28，除去它本身28外，其余5个数相加，1+2+4+7+14=28。
 * 输入n，请输出n以内(含n)完全数的个数。
 */
public class PerfectNumber {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        if (n < 6) {
            System.out.println(0);
        } else {
            int count = 1;
            for (int i = 7; i <= n; i++) {
                if (is(i)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }


    public static boolean is(int num) {
        int sum = 1, size = (int) Math.sqrt(num);
        for (int i = 2; i <= size; i++) {
            if (num % i == 0) {
                sum = sum + i + num / i;
            }
        }
        return num == sum;
    }
}
