package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 现在有一种密码变换算法。
 * 九键手机键盘上的数字与字母的对应： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0，把密码中出现的小写字母都变成九键键盘对应的数字，如：a 变成 2，x 变成 9.
 * 而密码中出现的大写字母则变成小写之后往后移一位，如：X ，先变成小写，再往后移一位，变成了 y ，例外：Z 往后移是 a 。
 * 数字和其它的符号都不做变换。
 * 数据范围： 输入的字符串长度满足 1 \le n \le 100 \1≤n≤100
 */
public class ChatToInt {
    static char[] map = new char[26];

    static {
        map[0] = map[1] = map[2] = '2';
        map[3] = map[4] = map[5] = '3';
        map[6] = map[7] = map[8] = '4';
        map[9] = map[10] = map[11] = '5';
        map[12] = map[13] = map[14] = '6';
        map[15] = map[16] = map[17] = map[18] = '7';
        map[19] = map[20] = map[21] = '8';
        map[22] = map[23] = map[24] = map[25] = '9';
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int j = text.length();
        char[] arr = new char[j];

        for (int i = 0; i < j; i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                arr[i] = (char) (c == 'Z' ? c + 7 : c + 33);
            } else if (c >= 'a' && c <= 'z') {
                arr[i] = map[c - 'a'];
            } else {
                arr[i] = c;
            }
        }
        System.out.println(new String(arr));
    }
}
