package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StringSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.valueOf(br.readLine());
        String[] result = new String[count];
        for (int i = 0; i < count; i++) result[i] = br.readLine();
        StringBuilder sb = new StringBuilder();
        Arrays.sort(result);
        for (String w : result) sb.append(w).append('\n');
        System.out.println(sb.toString());
    }

}
