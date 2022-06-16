package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 对字符串中的所有单词进行倒排。
 * <p>
 * 说明：
 * <p>
 * 1、构成单词的字符只有26个大写或小写英文字母；
 * <p>
 * 2、非构成单词的字符均视为单词间隔符；
 * <p>
 * 3、要求倒排后的单词间隔符以一个空格表示；如果原字符串中相邻单词间有多个间隔符时，倒排转换后也只允许出现一个空格间隔符；
 * <p>
 * 4、每个单词最长20个字母；
 * <p>
 * I am a student
 * student a am I
 * $bo*y gi!r#l
 * l r gi y bo
 */
public class WordReverse {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        int head = chars.length - 1, tail = chars.length - 1;

        while (head >= 0) {
            while (head >= 0) {
                if ((chars[head] >= 'A' && chars[head] <= 'Z') || chars[head] >= 'a' && chars[head] <= 'z') {
                    head--;
                } else {
                    break;
                }
            }
            if (head <= tail) {
                sb.append(chars, head + 1, tail - head).append(" ");
                tail = head - 1;
                head = head - 1;
            }
        }
        System.out.println(sb.toString());

    }


}
