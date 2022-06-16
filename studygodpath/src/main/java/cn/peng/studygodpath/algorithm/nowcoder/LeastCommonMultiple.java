package cn.peng.studygodpath.algorithm.nowcoder;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
 * <p>
 * 数据范围：1 1≤a,b≤100000
 */
public class LeastCommonMultiple {
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] sb = br.readLine().split(" ");
//        int a = Integer.parseInt(sb[0]), b = Integer.parseInt(sb[1]);
        int a = 328, b = 7751;

//        int result = 1, i = 2, min = Math.min(a, b);
//        while (i <= min) {
//            if (a % i == 0 && b % i == 0) {
//                a = a / i;
//                b = b / i;
//                result *= i;
//                min = Math.min(a, b);
//                i = 2;
//            } else {
//                i++;
//            }
//        }
//        System.out.println(result * a * b);
        System.out.println(Arrays.toString("255.254.255.0".split("\\.")));
    }
}
