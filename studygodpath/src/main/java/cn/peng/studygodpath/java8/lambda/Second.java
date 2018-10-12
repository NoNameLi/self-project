package cn.peng.studygodpath.java8.lambda;

/**
 * Created by remote on 2017/7/7.
 */
public class Second {

    public static void main(String[] args) {
        Converter<String,Integer> strToInt = (from -> Integer.valueOf(from));
        int num = strToInt.converter("123456");
        System.out.println(num);

        Converter<Integer,String> intTOStr = from -> String.valueOf(from);
        System.out.println(intTOStr.converter(159));

        Second second = new Second();
        Converter<String,Integer> calMethod = second::sop;
        calMethod.converter("Hello Lambda");
    }

    public Integer sop(String object){
        System.out.println(object);
        return 12;
    }
}

@FunctionalInterface
interface Converter<F,T>{
    T converter(F from);

}
