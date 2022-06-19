package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataHandler {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int[] rules = toIntArray(br.readLine().split(" "));

        List<Integer> index = new ArrayList<>();
        int[] sortRules = Arrays.stream(rules).distinct().sorted().toArray();
        for (int m = 0, k = sortRules.length; m < k; m++) {
            index.add(0);
            for (int i = 0, j = data.length; i < j; i++) {
                if (data[i].contains(String.valueOf(sortRules[m]))) {
                    index.add(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = 0;
        for (int i = 0, k = 0, j = index.size(); i < j; i++) {
            if (index.get(i) == 0) {
                if (index.get(i + 1) != 0) {
                    sb.append(sortRules[k]).append(" ");
                    size++;
                }
            } else {
                sb.append(index.get(i)).append(" ");
                sb.append(data[index.get(i)]).append(" ");
                size += 2;
            }
        }
        sb.insert(0, " ").insert(0, size);
        System.out.println(sb);
    }

    public static int[] toIntArray(String[] data) {
        int[] arr = new int[data.length];
        for (int i = 1, j = data.length; i < j; i++) {
            arr[i] = Integer.parseInt(data[i]);
        }
        return arr;
    }
}
