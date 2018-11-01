package cn.peng.studygodpath.java8.io.util.operator;

public enum MetaOperatorEnum {

    ADD("+", OperatorPriority.ORDER),
    SUB("-", OperatorPriority.ORDER),

    MULTIPLY("*", OperatorPriority.PRIOR),
    DIVIDE("/", OperatorPriority.PRIOR),
    REMAINDER("%", OperatorPriority.PRIOR),
    ABSOLUTE("|", OperatorPriority.PRIOR),
    FACTORIAL("!", OperatorPriority.PRIOR),
    POWER("^", OperatorPriority.PRIOR),

    LEFT_BRACKET("(", OperatorPriority.FORCE_PRIOR),
    RIGHT_BRACKET(")", OperatorPriority.FORCE_PRIOR),

    EQUAL("=", null);


    private String operator;
    private OperatorPriority priority;

    private MetaOperatorEnum(String operator, OperatorPriority priority) {
        this.operator = operator;
        this.priority = priority;
    }

    public static MetaOperatorEnum getOperatorEnum(String operator) {
        for (MetaOperatorEnum e : values()) {
            if (e.operator.equals(operator)) {
                return e;
            }
        }
        return null;
    }

    public String getOperator() {
        return this.operator;
    }

    public boolean isOrder() {
        return this.priority == OperatorPriority.ORDER;
    }

    public boolean isPriority() {
        return this.priority == OperatorPriority.PRIOR;
    }

//    public boolean isForcePriority() {
//        return this.priority == OperatorPriority.FORCE_PRIOR;
//    }

    public boolean isLeft() {
        return this == MetaOperatorEnum.LEFT_BRACKET;
    }

    public boolean isRight() {
        return this == MetaOperatorEnum.RIGHT_BRACKET;
    }

    public boolean isEqual() {
        return this == MetaOperatorEnum.EQUAL;
    }


    private enum OperatorPriority {
        /**
         * 顺序
         */
        ORDER,
        /**
         * 优先
         */
        PRIOR,
        /**
         * 强制优先
         */
        FORCE_PRIOR
    }

}
