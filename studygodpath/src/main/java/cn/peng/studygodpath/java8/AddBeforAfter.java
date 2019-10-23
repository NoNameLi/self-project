package cn.peng.studygodpath.java8;

import org.testng.annotations.Test;

/**
 * 自增前 后
 */
public class AddBeforAfter {

    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String[] args) {
        x--;
        test();
        System.out.println(x + y++ + x);
    }

    public static void test() {
        y = x++ + ++x;
    }

    @Test
    public void testSelfAddEqula() {
        int j = 99;
        j = j++;
        System.out.println(j);
        j = ++j;
        System.out.println(j);
    }

    @Test
    public void testGetOneCount() {
        System.out.println(this.getOneCount(200001));
    }

    public int getOneCount(int n) {
        int count = 0;
        int j = 0;
        for (int i = 0; i <= n; i++, j = i) {
            while (j != 0) {
                int lastByte = j % 10;
                if (lastByte == 1) {
                    count++;
                }
                j = j / 10;
            }
        }
        return count;
    }

}
