package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 求一个int类型数字对应的二进制数字中1的最大连续数，例如3的二进制为00000011，最大连续2个1
 * 数据范围：数据组数：1≤t≤5  ，1≤n≤500000
 * 进阶：时间复杂度：O(logn)  ，空间复杂度：O(1)
 */
public class IntMaxOneCount {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine()), curr = 0, max = 0;
        //11001000
        while (num > 0) {
            if (num % 2 == 1) {
                curr++;
            } else {
                if (curr > max)
                    max = curr;
                curr = 0;
            }
            num = num >>> 1;
        }
        if (curr > max)
            max = curr;
        System.out.println(max);
    }
}
