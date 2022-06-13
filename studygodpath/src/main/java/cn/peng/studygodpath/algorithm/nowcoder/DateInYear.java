package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 根据输入的日期，计算是这一年的第几天。
 * 保证年份为4位数且日期合法。
 * 进阶：时间复杂度：O(n)\O(n) ，空间复杂度：O(1)\O(1)
 */
public class DateInYear {

    // 1 3 5 7 8 10 12
    public static int[] monthDayNum = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int year = Integer.parseInt(line[0]);
        int month = Integer.parseInt(line[1]);
        int sum = Integer.parseInt(line[2]);
        if (month > 2 && (year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
            sum++;
        }
        for (int i = 1; i < month; i++) {
            sum = sum + monthDayNum[i];
        }
        System.out.println(sum);
    }
}
