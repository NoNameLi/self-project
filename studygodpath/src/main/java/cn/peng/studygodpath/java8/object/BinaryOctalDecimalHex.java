package cn.peng.studygodpath.java8.object;

/**
 * 二进制 0b开头
 * 八进制 0开头
 * 十进制
 * 十六进制 0x开头
 */
public class BinaryOctalDecimalHex {

    public static void main(String[] args) {
        System.out.println(0b1011);
        assert 1 < 0 : "1 > 0";
        assert 1 > 0 : "1 < 0";
        System.out.println(0e1011);

        System.out.println("valus is :" + (5 < 5 ? 8.9 : 9));

        System.out.println((int)'z');
        System.out.println(false ? 65536 : 'x');

        System.out.println(32 >> 1);
        System.out.println(32 >> 2);
        System.out.println(32 >> 3);
        System.out.println(32 >> 4);
        System.out.println(32 >> 5);
        System.out.println(32 >> 6);
        System.out.println(32 >> 7);
        System.out.println(32 >> 8);
        System.out.println(32 >> 9);
        System.out.println(32 >> 33);
        System.out.println(32 >> 32);
    }

}
