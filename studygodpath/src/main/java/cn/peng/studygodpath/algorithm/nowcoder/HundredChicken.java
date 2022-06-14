package cn.peng.studygodpath.algorithm.nowcoder;

/**
 * 公元五世纪，我国古代数学家张丘建在《算经》一书中提出了“百鸡问题”：鸡翁一值钱五，鸡母一值钱三，鸡雏三值钱一。百钱买百鸡，问鸡翁、鸡母、鸡雏各几何？
 * 现要求你打印出所有花一百元买一百只鸡的方式。
 */
public class HundredChicken {

    public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         // 注意 hasNext 和 hasNextLine 的区别
//         while (in.hasNextInt()) { // 注意 while 处理多个 case
//             int a = in.nextInt();
//             printResult(100);
//         }
        printResult(100);
    }

    public static void printResult(int money) {
        // 公鸡 5 母鸡 3  三小鸡 1
        int n = money / 5, m = money / 3, p = money * 3;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k = k + 3) {
                    if (i * 5 + j * 3 + k / 3 == money) {
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
    }
}
