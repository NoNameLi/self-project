package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-10 16:29
 * QDescription:
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * <p>
 * 数据范围：输入的字符串长度满足 1≤n≤20 1 \le n \le 20 \ 1≤n≤20  ，保证输入的字符串中仅出现小写字母
 */
public class DeleteMinChar {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int[] charNum = new int[26];
        for (int i = 0, j = text.length(); i < j; i++) {
            int point = text.charAt(i) - 'a';
            charNum[point] += 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (charNum[i] > 0 && charNum[i] < min) {
                min = charNum[i];
            }
        }
    }





}
