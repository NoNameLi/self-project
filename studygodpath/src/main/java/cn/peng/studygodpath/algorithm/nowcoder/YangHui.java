package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 三角形的数阵，第一行只有一个数1，以下每行的每个数，是恰好是它上面的数、左上角数和右上角的数，3个数之和（如果不存在某个数，认为该数就是0）。
 * <p>
 * 求第n行第一个偶数出现的位置。如果没有偶数，则输出-1。例如输入3,则输出2，输入4则输出3，输入2则输出-1。
 * 1                   1  -1
 * 1  1  1                2  -1 奇 奇 奇
 * 1  2  3  2  1             3   2 奇 偶 奇
 * 1  3  6  7  6  3  1          4   3 奇 奇 偶
 * 1 4  10 16 19 16 10 4 1        5   2 奇 偶 偶
 * 1 5 15 30 45                     6   4 奇 奇 奇
 */
public class YangHui {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int row;
        while (line != null) {
            row = Integer.parseInt(line);
            if (row < 3) {
                System.out.println(-1);
            } else if (row % 4 == 1 || row % 4 == 3) {
                System.out.println(2);
            } else if (row % 4 == 0) {
                System.out.println(3);
            } else {
                System.out.println(4);
            }
            line = reader.readLine();
        }
    }

}
