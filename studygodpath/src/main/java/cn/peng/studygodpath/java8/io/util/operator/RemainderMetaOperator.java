package cn.peng.studygodpath.java8.io.util.operator;

/**
 * 整数 求余
 */
public class RemainderMetaOperator implements ArithmeticOperator {
    @Override
    public Number operator(Number a, Number b) {
        return a.intValue() % b.intValue();
    }
}
