package cn.peng.studygodpath.java8.io.util.operator;

public class OperatroStaticFactory {
    public static ArithmeticOperator createOperator(MetaOperatorEnum e) {
        ArithmeticOperator operator = null;
        switch (e) {
            case ADD:
                operator = new AddMetaOperator();
                break;
            case SUB:
                operator = new SubMetaOperator();
                break;
            case POWER:
                operator = new PowerMetaOperator();
                break;
            case DIVIDE:
                operator = new DivideMetaOperator();
                break;
            case ABSOLUTE:
                operator = new AbsoluteMetaOperator();
                break;
            case MULTIPLY:
                operator = new MultiplyMetaOperator();
                break;
            case FACTORIAL:
                operator = new FactorialMetaOperator();
                break;
            case REMAINDER:
                operator = new RemainderMetaOperator();
                break;
        }
        return operator;
    }
}
