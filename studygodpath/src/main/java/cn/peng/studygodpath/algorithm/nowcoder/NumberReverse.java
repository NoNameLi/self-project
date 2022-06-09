package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.TreeSet;

/**
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 */
public class NumberReverse {

    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        int available = is.available() - 1;
        char[] arr = new char[available];
        while (available-- > 0) {
            arr[available] = (char) is.read();
        }
        String result = String.valueOf(arr);
        System.out.println(result);
        StringBuilder sb = null;
        sb.reverse().toString();
    }
}
