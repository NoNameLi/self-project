package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 判断短字符串S中的所有字符是否在长字符串T中全部出现。
 * 请注意本题有多组样例输入。
 * 数据范围:1≤len(S),len(T)≤200
 * 进阶：时间复杂度：O(n)  ，空间复杂度：O(n)
 * <p>
 * bc
 * abc
 * true
 */
public class ShortLongStr {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String shortStr = br.readLine();
        String longStr = br.readLine();
        boolean[] shortHas = new boolean[26];
        boolean[] longHas = new boolean[26];
        for (int i = 0, j = shortStr.length(); i < j; i++) {
            shortHas[shortStr.charAt(i) - 'a'] = true;
        }
        for (int i = 0, j = longStr.length(); i < j; i++) {
            longHas[longStr.charAt(i) - 'a'] = true;
        }
        boolean result = true;
        for (int i = 0, j = shortHas.length; i < j; i++) {
            if (shortHas[i] && !longHas[i]) {
                result = false;
                break;
            }
        }
        System.out.print(result);
    }
}
