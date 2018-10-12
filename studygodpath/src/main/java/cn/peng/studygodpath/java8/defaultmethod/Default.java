package cn.peng.studygodpath.java8.defaultmethod;

/**
 * java8 赋予接口（interface）新的功能：默认方法
 *  可以在接口中实现接口共通的方法，实现接口者也可以重写默认方法
 * Created by remote on 2017/7/7.
 */
public interface Default {
    double calculate(double num);

    default double sqrt(double value){
        return Math.sqrt(value);
    }

    default  void two(){
        System.out.println(Default.class.getSimpleName());
    }


    static void add(){
        System.out.println("interface static");
    }
}
