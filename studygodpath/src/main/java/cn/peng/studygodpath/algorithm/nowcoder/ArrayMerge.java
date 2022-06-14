package cn.peng.studygodpath.algorithm.nowcoder;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 题目标题：
 * 将两个整型数组按照升序合并，并且过滤掉重复数组元素。
 * 输出时相邻两数之间没有空格。
 * <p>
 * 输入描述：
 * <p>
 * 输入说明，按下列顺序输入：
 * 1 输入第一个数组的个数
 * 2 输入第一个数组的数值
 * 3 输入第二个数组的个数
 * 4 输入第二个数组的数值
 * 输出描述：
 * <p>
 * 输出合并之后的数组
 */
public class ArrayMerge {

    public static void main(String[] args) throws Exception {
//        method1();方法快一点啊，应该是数量级别比较低
        method2();
    }

    public static void method2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(br.readLine());
        String[] arr1 = br.readLine().split(" ");
        int n2 = Integer.parseInt(br.readLine());
        String[] arr2 = br.readLine().split(" ");
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n1; i++) {
            treeSet.add(Integer.parseInt(arr1[i]));
        }
        for (int i = 0; i < n2; i++) {
            treeSet.add(Integer.parseInt(arr2[i]));
        }
        Iterator<Integer> iterator = treeSet.iterator();
        for (; iterator.hasNext(); ) {
            System.out.print(iterator.next());
        }
    }

    public static void method1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(br.readLine());
        String[] arr1 = br.readLine().split(" ");
        int n2 = Integer.parseInt(br.readLine());
        String[] arr2 = br.readLine().split(" ");

        int[] result = new int[n1 + n2];
        int length = 0;
        for (int i = 0; i < n1; i++) {
            length = insertOrder(result, length, Integer.parseInt(arr1[i]));
        }
        for (int i = 0; i < n2; i++) {
            length = insertOrder(result, length, Integer.parseInt(arr2[i]));
        }
        for (int i = 0; i < length; i++) {
            System.out.print(result[i]);
        }
    }

    public static int insertOrder(int[] arr, int length, int element) {
        for (int i = length - 1; i >= 0; i--) {
            if (element == arr[i]) {
                for (int j = i + 1; j < length; j++) {// 还原
                    arr[j] = arr[j + 1];
                }
                return length;
            } else if (element < arr[i]) {
                arr[i + 1] = arr[i];
            } else {
                arr[i + 1] = element;
                return length + 1;
            }
        }
        arr[0] = element;
        return length + 1;
    }

}
