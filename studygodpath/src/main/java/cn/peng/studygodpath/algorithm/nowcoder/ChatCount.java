package cn.peng.studygodpath.algorithm.nowcoder;

import java.util.Scanner;

/**
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3 。
 */
public class ChatCount {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            int[] arr = new int[128];
            for (int i = a.length() - 1; i >= 0; i--) {
                int index = (int) a.charAt(i);
                if (index >= 0 && index < 128) {
                    arr[index] += 1;
                }
            }
            int count = 0;
            for (int i = 0; i < 128; i++) {
                if (arr[i] > 0) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
