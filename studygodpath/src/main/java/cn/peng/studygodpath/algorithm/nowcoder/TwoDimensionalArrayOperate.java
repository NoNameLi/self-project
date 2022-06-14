package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 有一个m∗n 大小的数据表，你会依次进行以下5种操作：
 * 1.输入m  和n  ，初始化m∗n 大小的表格。
 * 2.输入x1​、y1​、x2​、y2​，交换坐标在(x1,y1)(x1​,y1​)和(x2,y2)(x2​,y2​)的两个数。
 * 3.输入x ，在第x 行上方添加一行。
 * 4.输入y，在第y  列左边添加一列。
 * 5.输入x 、y，查找坐标为(x,y) (x,y)\ (x,y) 的单元格的值。
 * <p>
 * 请编写程序，判断对表格的各种操作是否合法。
 * <p>
 * 详细要求:
 * 1.数据表的最大规格为9行*9列，对表格进行操作时遇到超出规格应该返回错误。
 * 2.对于插入操作，如果插入后行数或列数超过9了则应返回错误。如果插入成功了则将数据表恢复至初始化的m∗n大小，多出的数据则应舍弃。
 * <p>
 * 3.所有输入坐标操作，对m∗n 大小的表格，行号坐标只允许0~m-1，列号坐标只允许0~n-1。超出范围应该返回错误。
 * 本题含有多组样例输入！行列从0开始标号
 * 数据范围：数据组数：1≤t≤5
 * 进阶：时间复杂度：O(1) ) ，空间复杂度：O(1)
 * 输入描述：
 * <p>
 * 输入数据按下列顺序输入：
 * 1 表格的行列值
 * 2 要交换的两个单元格的行列值
 * 3 输入要插入的行的数值
 * 4 输入要插入的列的数值
 * 5 输入要查询的单元格的坐标
 * 输出描述：
 * <p>
 * 输出按下列顺序输出：
 * 1 初始化表格是否成功，若成功则返回0， 否则返回-1
 * 2 输出交换单元格是否成功
 * 3 输出插入行是否成功
 * 4 输出插入列是否成功
 * 5 输出查询单元格数据是否成功
 */
public class TwoDimensionalArrayOperate {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] nm = line.split(" ");
            TwoDimensional twoDimensional = new TwoDimensional(Integer.parseInt(nm[0]), Integer.parseInt(nm[1]));

            String[] x1y1x2y2 = br.readLine().split(" ");
            System.out.println(twoDimensional.enableSwap(Integer.parseInt(x1y1x2y2[0]), Integer.parseInt(x1y1x2y2[1]), Integer.parseInt(x1y1x2y2[2]), Integer.parseInt(x1y1x2y2[3])));
            System.out.println(twoDimensional.enableInsertRow(Integer.parseInt(br.readLine())));
            System.out.println(twoDimensional.enableInsertCol(Integer.parseInt(br.readLine())));
            String[] xy = br.readLine().split(" ");
            System.out.println(twoDimensional.enableGetValue(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));

        }

    }

    static class TwoDimensional {
        public static int ROW_MAX = 9;
        public static int COL_MAX = 9;

        int[][] arr = null;

        // 4行 7列
        public TwoDimensional(int x, int y) {
            if (y > ROW_MAX || x > COL_MAX) {
                System.out.println(-1);
                return;
            }
            arr = new int[y][x];
            System.out.println(0);
        }

        private boolean inRange(int x, int y) {
            return x < arr[0].length && y < arr.length;
        }

        public int enableSwap(int x1, int y1, int x2, int y2) {
            if (inRange(x1, y1) && inRange(x2, y2)) {
                return 0;
            } else {
                return -1;
            }
        }

        public int enableInsertRow(int x) {
            if (arr[0].length + 1 > ROW_MAX || x > arr[0].length-1) {
                return -1;
            } else {
                return 0;
            }
        }

        public int enableInsertCol(int y) {
            if (arr.length + 1 > COL_MAX || y > arr.length-1) {
                return -1;
            } else {
                return 0;
            }
        }

        public int enableGetValue(int x, int y) {
            if (inRange(x, y)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
