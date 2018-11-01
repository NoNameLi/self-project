package cn.peng.studygodpath.java8.io.util.operator;


@FunctionalInterface
public interface ArithmeticOperator {
    Number operator(Number a, Number b);
}
