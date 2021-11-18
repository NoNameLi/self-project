package cn.peng.studygodpath.java8.defaultmethod;

/**
 * Created by remote on 2017/7/7.
 */
public class DefaultMethod implements Default, Default2 {
    @Override
    public double calculate(double num) {
        return num;
    }

    @Override
    public void two() {

    }

    public static void main(String[] args) {

        Default aDefault = new Default() {
            @Override
            public double calculate(double num) {
                return num * num;
            }

            @Override
            public double sqrt(double value) {
                return Math.pow(value, 2);
            }
        };
        Default.interfaceStatisMethod();// 接口定义的静态方法只能通过接口类调用，
        System.out.println(aDefault.calculate(10));
        System.out.println(aDefault.sqrt(10));

        DefaultMethod method = new DefaultMethod();
        System.out.println(method.calculate(10.00));
        System.out.println(method.sqrt(100.00));
    }

}

