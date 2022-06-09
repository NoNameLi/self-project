package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntBirOneCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.valueOf(br.readLine());
        int count = 0;
        if (a < 0) {
            a = a * -1;
            count = 1;
        }
        while (a != 0) {
            count += a & 1;
            a = a >> 1;
        }
        System.out.println(count);
    }
}
