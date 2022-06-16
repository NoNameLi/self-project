package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IPTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        int[] data = new int[7];
        while ((line = br.readLine()) != null) {
            String[] ipMark = line.split("~");
            if (ipMark[0].startsWith("0.") || ipMark[0].startsWith("127.")) {
                continue;
            }
            if (isMark(ipMark[1], data)) {
                System.out.println(ipMark[1]);
                ipType(ipMark[0], data);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(data[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static int[] toFourNumber(String[] arr) {
        int[] intArr = new int[4];
        intArr[0] = Integer.parseInt(arr[0]);
        intArr[1] = Integer.parseInt(arr[1]);
        intArr[2] = Integer.parseInt(arr[2]);
        intArr[3] = Integer.parseInt(arr[3]);
        return intArr;
    }

    public static boolean isFourNumber(String[] arr) {
        if (arr.length != 4) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            char[] item = arr[i].toCharArray();
            for (int j = 0; j < item.length; j++) {
                if (item[j] < '0' || item[j] > '9') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSmall256(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 255) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne(int[] arr) {
        if (arr[arr.length - 1] % 2 == 1) {
            return false;
        }
        if ((arr[0] >>> 7) % 2 == 0) {
            return false;
        }
        boolean first = false;
        for (int i = arr.length - 1; i >= 0; i--) {
            int item = arr[i];
            if (first && item == 0) {
                return false;
            }
            while (item > 0) {
                if (!first && item % 2 == 1) {
                    first = true;
                } else if (first && item % 2 == 0) {
                    return false;
                }
                item = item >> 1;
            }
        }
        return true;
    }


    public static boolean isMark(String mark, int[] data) {
        String[] arr = mark.split("\\.");
        if (isFourNumber(arr)) {
            int[] numArr = toFourNumber(arr);
            if (isSmall256(numArr) && isOne(numArr)) {
                return true;
            }
        }
        data[5] += 1;
//          System.out.println(Arrays.toString(arr));
        return false;
    }

    public static void ipType(String ip, int[] data) {
        String[] arr = ip.split("\\.");
        if (isFourNumber(arr)) {
            int[] ipArr = toFourNumber(arr);
            if (isSmall256(ipArr)) {
                if (ipArr[0] <= 126) {
                    data[0] += 1;
                } else if (ipArr[0] > 127 && ipArr[0] < 192) {
                    data[1] += 1;
                } else if (ipArr[0] < 224) {
                    data[2] += 1;
                } else if (ipArr[0] < 240) {
                    data[3] += 1;
                } else if (ipArr[0] < 256) {
                    data[4] += 1;
                }
                if (ipArr[0] == 10 || ipArr[0] == 127 || (ipArr[0] == 192 && ipArr[1] == 168)) {
                    data[6] += 1;
                }
                return;
            }
        }
        data[5] += 1;
    }


}
