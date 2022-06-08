package cn.peng.studygodpath.algorithm.nowcoder;

import java.util.Scanner;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-08 17:14
 * QDescription: 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
 */
public class GetPrimeFactor {

    public static boolean isPrimeOptimize(int num) {
        for (int i = 2; i <= num / i; i++) // sqrt(num)  num /i
            if (num % i == 0)
                return false;
        return true;
    }

    public static int getNextPrime(int origin) {
        while (true) {
            if (isPrimeOptimize(++origin)) {
                return origin;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long num = scanner.nextLong();
        long k = (long) Math.sqrt(num);

        for (long i = 2; i <= k; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "": num+" ");
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int a = in.nextInt();
//            int i = 2;
//
//            while (a != 1) {
//                if (isPrimeOptimize(a)) {
//                    System.out.print(a);
//                    System.out.print(' ');
//                    break;
//                } else if (a % i == 0 && isPrimeOptimize(i)) {
//                    System.out.print(i);
//                    System.out.print(' ');
//                    a = a / i;
//                } else {
//                    i++;
//                }
//            }
//
//        }
    }
}