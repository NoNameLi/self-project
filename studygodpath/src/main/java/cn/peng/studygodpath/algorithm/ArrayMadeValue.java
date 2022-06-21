package cn.peng.studygodpath.algorithm;

/**
 * 判断数组 能否组成要求的值
 */
public class ArrayMadeValue {

    public static void main(String[] args) {
        int[] arr = {-4, 1, 2, -4};
        System.out.println(isMade(arr, -2));
    }


    public static boolean isMade(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
            int sum = arr[i], index = 0;
            while (index < arr.length) {
                for (int j = index; j < arr.length; j++) {
                    if (i != j) {
                        sum = sum + arr[j];
                        if (sum == target) {
                            return true;
                        }
                    }
                }
                index++;
            }
        }
        return false;
    }
}
