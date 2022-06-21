package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class IpBelong {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int markNum = getMarkNum(br.readLine());
        int ip1Num = getIpNum(br.readLine());
        int ip2Num = getIpNum(br.readLine());
        StringBuilder sb = new StringBuilder();
        boolean flag = sb.charAt(0) == ' ';
        if (markNum == 0 || ip1Num == 0 || ip2Num == 0) {
            System.out.println(1);
        } else if ((ip1Num & markNum) == (ip2Num & markNum)) {
            System.out.println(0);
        } else {
            System.out.println(2);
        }

    }

    public static int getIpNum(String ip) {
        String[] arr = ip.split("\\.");
        if (arr.length == 4) {
            int[] num = new int[4];
            for (int i = 0; i < 4; i++) {
                num[i] = Integer.parseInt(arr[i]);
                if (num[i] < 0 || num[i] > 255) {
                    return 0;
                }
            }
            int tmp = num[0] << 24 | num[1] << 16 | num[2] << 8 | num[3];
            return tmp;
        }
        return 0;
    }

    public static int getMarkNum(String mark) {
        int markNum = getIpNum(mark);
        if (markNum >= 0 || markNum % 2 == -1 || markNum % 2 == 1)
            return 0;
        boolean first = false;
        int tmp = markNum;
        while (tmp != 0) {
            if (!first && tmp % 2 == 1) {
                first = true;
            }
            if (first && tmp % 2 == 0) {
                return 0;
            }
            tmp = tmp >>> 1;
        }
        return markNum;
    }
}
