package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-10 14:34
 * QDescription: 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足 1≤n≤100 1 \le n \le 100 \ 1≤n≤100
 */
public class Bottle {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int num = Integer.valueOf(br.readLine());
            if (num == 0) {
                break;
            }
            System.out.println(cal(num));
        }
    }

    public static int cal(int bottleNum) {
        int a = bottleNum, bb = 0;

        while (a >= 3) {
            int b = a / 3;
            bb += b;
            a = a % 3 + b;
        }
        if (a == 2) {
            bb += 1;
        }
        return bb;
    }

}
