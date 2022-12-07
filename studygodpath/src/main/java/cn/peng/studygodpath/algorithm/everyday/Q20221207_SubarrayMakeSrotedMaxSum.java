package cn.peng.studygodpath.algorithm.everyday;

// 来自Amazon
// 定义一个概念叫"变序最大和"
// "变序最大和"是说一个数组中，每个值都可以减小或者不变，
// 在必须把整体变成严格升序的情况下，得到的最大累加和
// 比如，[1,100,7]变成[1,6,7]时，就有变序最大和为14
// 比如，[5,4,9]变成[3,4,9]时，就有变序最大和为16
// 比如，[1,4,2]变成[0,1,2]时，就有变序最大和为3
// 给定一个数组arr，其中所有的数字都是>=0的
// 求arr所有子数组的变序最大和中，最大的那个并返回  注意求子数组中的
// 1 <= arr长度 <= 10^6
// 0 <= arr[i] <= 10^6
public interface Q20221207_SubarrayMakeSrotedMaxSum {

    // 为了验证
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    // 正确结果 为了验证 时间复杂度O(N * V)的方法
    static long correctResult(int[] arr) {
        int n = arr.length;
        int max = 0;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        long ans = 0;
        long[][] dp = new long[n][max + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= max; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, process1(arr, i, arr[i], dp));
        }
        return ans;
    }

    static long process1(int[] arr, int i, int p, long[][] dp) {
        if (p <= 0 || i == -1) {
            return 0;
        }
        if (dp[i][p] != -1) {
            return dp[i][p];
        }
        int cur = Math.min(arr[i], p);
        long next = process1(arr, i - 1, cur - 1, dp);
        long ans = (long) cur + next;
        dp[i][p] = ans;
        return cur + next;
    }


    static int solution1(int[] arr) {
        int ans = 0, max = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 横坐标为 数组下标，纵坐标为 下表对应的值，代表此时的 变序和 或者改用map结构
        int[][] dp = new int[arr.length][max + 1];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= max; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            ans = Math.max(cal(arr, i, dp), ans);
        }
        return ans;
    }

    static int cal(int[] arr, int tail, int[][] dp) {
        int ans = arr[tail], next = arr[tail];
        for (int i = tail - 1; i >= 0; i--) {
            if (next == 0) {
                break;
            }
            if (dp[i][next] != -1) {
                return dp[i][next] + ans;
            }
            next = Math.min(arr[i], next - 1);
            ans += next;
        }
        return ans;
    }


    static long solution2(int[] arr) {
        int n = arr.length;
        // 只放下标，只要有下标，arr可以拿到值
        int[] stack = new int[n];
        int size = 0;
        long[] dp = new long[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // i -> arr[i] 依次把收益！得到！
            int curVal = arr[i];
            int curIdx = i;
            //        20
            //        17
            while (curVal > 0 && size > 0) {
                //  100
                //  16
                int leftIdx = stack[size - 1];
                int leftVal = arr[leftIdx];
                if (leftVal >= curVal) {
                    size--;
                } else {
                    // leftVal < curVal
                    //       8     20
                    //      15     17
                    int idxDiff = curIdx - leftIdx;
                    int valDiff = curVal - leftVal;

                    //  12           2
                    //             8  19 20
                    //            15  16 17
                    if (valDiff >= idxDiff) {
                        dp[i] += sum(curVal, idxDiff) + dp[leftIdx];
                        curVal = 0;
                        curIdx = 0;
                        break;
                    } else {
                        //   18          20
                        //   13 14 15 16 17
                        //      17 18 19 20
                        //   16
                        dp[i] += sum(curVal, idxDiff);
                        //   16
                        //   13
                        curVal -= idxDiff;
                        curIdx = leftIdx;
                        size--;
                    }
                }
            }
            if (curVal > 0) {
                dp[i] += sum(curVal, curIdx + 1);
            }
            stack[size++] = i;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    static long sum(int a, int b) {
        b = Math.min(a, b);
        return (((long) a * 2 - b + 1) * b) / 2;
    }

    static void main(String[] args) {
        int N = 10;
        int V = 100;
        int testTimes = 50000;
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            long ans1 = correctResult(arr);
            long ans2 = solution1(arr);
            if (ans1 != ans2) {
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }

    }


}
