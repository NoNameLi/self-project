package cn.peng.studygodpath.java8.io.util.operator;


public class PowerMetaOperator implements ArithmeticOperator {
    @Override
    public Number operator(Number a, Number b) {
        return Math.pow(a.doubleValue(),b.doubleValue());
    }
}
