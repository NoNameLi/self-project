package cn.peng.studygodpath.java8.sourcecode.string;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.SortedSet;

/**
 * String 拼接
 * Created by remote on 2017/10/26.
 */
public class String171026 {


    public void studyConcatMethond() {
        String source = "Yes";
        System.out.println(source.concat("No"));
    }

    @Test
    public void testBigInteger() {
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("2");
        BigInteger sum = BigInteger.ZERO;
        sum.add(a).add(b);
        System.out.println(sum);
    }

    @Test
    public void studyIntern() {
        String cacheStr = "abc";
        String str = new String("abc");
        System.out.println("cacheStr == str？，" + (cacheStr == str));
        System.out.println("cacheStr == str.intern()?," + (cacheStr == str.intern()));
        char c = 'a';

    }

    public static void main(String[] args) {
        new String171026().studyConcatMethond();
    }


    public void testChiness() {
        String str = "牛ab测uf滚";

        str.charAt(0);

    }

    /**
     * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
     * 数字：[0x30,0x39]（或十进制[48, 57]）
     * 小写字母：[0x61,0x7a]（或十进制[97, 122]）
     * 大写字母：[0x41,0x5a]（或十进制[65, 90]）
     * Character.UnicodeBlock
     * @param c
     */
    public boolean isChiness(char c) {
        boolean isGB2312 = false;
        byte[] bytes = ("" + c).getBytes();
        if (bytes.length == 2) {
            int[] ints = new int[2];
            ints[0] = bytes[0] & 0xff;
            ints[1] = bytes[1] & 0xff;
            if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                isGB2312 = true;
            }
        }
        return isGB2312;
    }

    @Test
    public void JosephRing() {
        boolean[] arr = new boolean[30];
        int deasCount = 0;
        int point = 1;
        int position = 0;
        for (; deasCount <= 15; position++) {
            if (point == 9) {
                arr[position] = true;
                point = 1;
                deasCount++;
            }
            if (position >= 30) {
                position = 0;
            }
            if (arr[position] == false) {
                point++;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
