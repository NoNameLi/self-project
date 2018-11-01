package cn.peng.studygodpath.java8.io.util.operator;


public class AbsoluteMetaOperator implements ArithmeticOperator {
    @Override
    public Number operator(Number a, Number b) {
        return Math.abs(a.doubleValue());
    }
}
