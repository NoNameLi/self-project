package cn.peng.studygodpath.java8.io.util.operator;

/**
 * 正整数 乘阶
 */
public class FactorialMetaOperator implements ArithmeticOperator {
    @Override
    public Number operator(Number a, Number b) {
        int num = a.intValue();
        if (num < 0) {
            throw new IllegalArgumentException("乘阶" + a);
        }
        int result = 1;
        for (int i = 0; i < num; i++) {
            result *= (i + 1);
        }
        return result;
    }
}
