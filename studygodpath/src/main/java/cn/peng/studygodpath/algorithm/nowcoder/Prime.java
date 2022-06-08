package cn.peng.studygodpath.algorithm.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-08 15:49
 * QDescription:求素数  只能被1和本身 整除的数
 */
public class Prime {

    /**
     * 获取一个范围内的素数
     */
    public static boolean isPrime(int num) {
        for (int i = 2; i < num; i++)
            if (num % i == 0)
                return false;
        return true;
    }

    public static boolean isPrimeOptimize(int num) {
        for (int i = 2; i <= num / i; i++) // sqrt(num)  num /i
            if (num % i == 0)
                return false;
        return true;
    }

    // 获取范围内素数，获取到素数后，将范围内这个数的倍数过滤掉，
    public static List<Integer> getPrimeFromRangeOptimize(int min, int max) {
        List<Integer> arr = new ArrayList<>((max - min) / 2);
        boolean[] flag = new boolean[max - min];
        for (int i = min; i < max; i++) {
            if (!flag[i - min] && isPrimeOptimize(i)) {
                arr.add(i);
                // 标记倍数
                for (int j = 2 * i; j < max; j += i) {
                    flag[j - min] = true;
                }
            }
        }
        return arr;
    }


    public static List<Integer> getPrimeFromRange(int min, int max) {
        List<Integer> arr = new ArrayList<>((max - min) / 2);
        for (int i = min; i <= max; i++) {
            if (isPrime(i)) {
                arr.add(i);
            }
        }
        return arr;
    }

    public static int getNextPrime(int origin) {
        while (true) {
            if (isPrime(++origin)) {
                return origin;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getPrimeFromRange(2,100).toString());
        System.out.println(getPrimeFromRangeOptimize(2,100).toString());
        System.out.println(getNextPrime(5));
    }
}
