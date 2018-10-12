package cn.peng.studygodpath.java8.lambda;

/**
 * Created by remote on 2017/7/7.
 */
public class Third {
    public static int offset = 23;


    public static void main(String[] args) {

        Converter<Integer,String> con = (from)->String.valueOf(from + offset);
        System.out.println(con.converter(45));
        offset = 6;
        System.out.println(con.converter(45));
    }


}
