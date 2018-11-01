package cn.peng.studygodpath.java8.io.util;

import cn.peng.studygodpath.java8.io.util.operator.ArithmeticOperator;
import cn.peng.studygodpath.java8.io.util.operator.MetaOperatorEnum;
import cn.peng.studygodpath.java8.io.util.operator.OperatroStaticFactory;
import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 计算器

 * 后缀表达式的计算方法，就是：将后缀表达式从左到右依次遍历，如果当前元素为数字则入（操作数）栈，
 * 如果为操作符，则pop出栈顶两个元素（第一次pop出的是右操作数，第二次pop出的是左操作数）进行运算，然后将计算结果再次入栈，直至表达式结束，
 * 此时操作数栈内理应只剩一个元素即表达式结果
 * <p>
 * 操作符有两个级别
 * 顺序：+ -
 * 优先： * / % ! || ^
 * 强制优先：()
 * <p>
 * 关键的是：将中缀 转换成 后缀
 * <p>
 * prefix
 * Infix
 * Suffix
 */
public class Calculator {

    public float cal(String expriness){
        List<String> list = infixToSuffix(expriness);
        return suffixCalculator(list);
    }


    /**
     * 中缀转后缀
     * 不支持小数
     * a+b*(|c-f|^2-(d+e)) + g!%3=
     *
     * @return
     */
    private List<String> infixToSuffix(String expriness) {
        expriness = this.exprinessPretreatment(expriness);
        int length = expriness.length();
        Stack<MetaOperatorEnum> operatorStack = new Stack<>();
        ArrayList<String> exprinessList = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        MetaOperatorEnum currentOper = null;
        MetaOperatorEnum popOper = null;

        for (int i = 0; i < length; i++) {
            if (CharUtils.isAsciiNumeric(expriness.charAt(i))) {//TODO or 是 。
                num.append(expriness.charAt(i));
            } else {
                if (num.length() > 0) {
                    exprinessList.add(num.toString());
                }
                num.delete(0, num.length());
                currentOper = MetaOperatorEnum.getOperatorEnum(String.valueOf(expriness.charAt(i)));
                if (null == currentOper) {
                    throw new IllegalArgumentException(expriness);
                }
                if (currentOper.isPriority() || currentOper.isLeft()) {
                    operatorStack.push(currentOper);
                }

                if (currentOper.isOrder()) {
                    popOper = this.popOperatorToExpriness(operatorStack, exprinessList);
                    if (null != popOper && popOper.isLeft()) {
                        operatorStack.push(popOper);
                    }
                    operatorStack.push(currentOper);
                }
                if (currentOper.isRight()) {
                    popOper = this.popOperatorToExpriness(operatorStack, exprinessList);
                    if (popOper != MetaOperatorEnum.LEFT_BRACKET) {
                        throw new IllegalArgumentException(expriness);
                    }
                }
                if (currentOper.isEqual()) {
                    this.popOperatorToExpriness(operatorStack, exprinessList);
                }
            }
        }
        return exprinessList;
    }

    private String exprinessPretreatment(String expriness) {
        expriness = expriness.replaceAll(" ", "");
        if (!expriness.endsWith(MetaOperatorEnum.EQUAL.getOperator())) {
            expriness += MetaOperatorEnum.EQUAL.getOperator();
        }
        return expriness;
    }

    /**
     * 将栈中的操作符 出栈到表达式列表中， 返回最后的出栈元素
     *
     * @param operatorStack
     * @param exprinessList
     * @return
     */
    private MetaOperatorEnum popOperatorToExpriness(Stack<MetaOperatorEnum> operatorStack, List<String> exprinessList) {
        MetaOperatorEnum popOper = null;
        for (; null != operatorStack && !operatorStack.empty(); ) {
            popOper = operatorStack.pop();
            if (popOper.isLeft())
                break;
            exprinessList.add(String.valueOf(popOper.getOperator()));
        }
        return popOper;
    }


    /**
     * 后缀表达式计算
     * 顺序计算
     *
     * @return
     */
    private float suffixCalculator(List<String> suffixList) {
        Stack<Number> num = new Stack<>();
        for (String s : suffixList) {
            MetaOperatorEnum operator = MetaOperatorEnum.getOperatorEnum(s);
            if (null == operator) {
                num.push(Float.valueOf(s));
            } else {
                ArithmeticOperator operatorMethod = OperatroStaticFactory.createOperator(operator);
                // 注意顺序
                Number a = num.pop();
                Number b = num.pop();
                num.push(operatorMethod.operator(b, a));
            }
        }
        return num.pop().floatValue();
    }

    @Test
    public void testConvert() {
//        List<String> list = infixToSuffix("12 * (5 + 6)");
//        System.out.println(Arrays.toString(list.toArray()));
        String expriness = "3+(2-5)*6/3";
        List<String> list = infixToSuffix(expriness);
        System.out.println(Arrays.toString(list.toArray()));
        float result = suffixCalculator(list);
        System.out.println(expriness + "="+ result);
    }
}
