package cn.peng.studygodpath.java8.io.util.operator;


public class MultiplyMetaOperator implements ArithmeticOperator {
    @Override
    public Number operator(Number a, Number b) {
        return a.floatValue() * b.floatValue();
    }
}
