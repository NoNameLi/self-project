package cn.peng.studygodpath.java8;

public class JavaNumber {

    private int a = 0XDada_Cafe;
    private float b = 0x1.fffffeP+127f;
    private float c = 1996;
    private double d = 1996.3;
    private double f = 9999e2;
    private double g = 33e2;
    private float h = 0x1.fffffep-12f;
    private long p = 0b1_1_1_0_1;


    public static void main(String[] args) {
        int i = -5;
        printByte(i);
    }

    public static void printByte(int num) {
        for(int i=31;i>=0;i--){
            System.out.print((num & 1 << i) == 0 ? "0":"1");
        }
    }
}
