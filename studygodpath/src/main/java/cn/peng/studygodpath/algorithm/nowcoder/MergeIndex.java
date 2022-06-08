package cn.peng.studygodpath.algorithm.nowcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-08 17:55
 * QDescription: 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 * 输入：
 * <p>
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * <p>
 * 输出：
 * <p>
 * 0 3
 * 1 2
 * 3 4
 */


public class MergeIndex {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            Map<Integer, Integer> map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                String[] line = in.nextLine().split(" ");
                if (map.containsKey(line[0])) {
                    map.put(line[0], map.get(line[0]) + line[1]);
                } else {
                    map.put(line[0], line[1]);
                }
            }
            map.keySet().stream().sorted().forEach(t -> {
                System.out.println(t + " " + map.get(t));
            });
        }
    }
}
