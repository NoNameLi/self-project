package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

//(12+112)+3*5-6/2
public class Calculator {

    public static byte[] optVal = new byte[256];

    static {
        optVal['+'] = 1;
        optVal['-'] = 1;
        optVal['*'] = 2;
        optVal['/'] = 2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        char[] chars = br.readLine().toCharArray();
        char[] chars = "3-10+(0+(10+5+3)-10)".toCharArray();
        LinkedList<Integer> numberStack = new LinkedList();
        LinkedList<Character> optStack = new LinkedList();

        StringBuilder numBuilder = new StringBuilder();
        boolean lastIsOpt = true;
        for (int i = 0; i < chars.length; i++) {
            if (isNum(chars[i])) {
                numBuilder.append(chars[i]);
                lastIsOpt = false;
            } else {
                if (chars[i] == '-' && lastIsOpt) {// 连续的符号，补0
                    numberStack.push(0);
                }
                if (numBuilder.length() > 0) {
                    numberStack.push(Integer.valueOf(numBuilder.toString()));
                    numBuilder.delete(0, numBuilder.length());
                }
                if (chars[i] == ')') {
                    while (optStack.getFirst() != '(') {
                        cal(numberStack, optStack);
                    }
                    optStack.pop();
                } else if (chars[i] == '(' || optStack.size() == 0 || isStackTopHight(chars[i], optStack)) {
                    optStack.push(chars[i]);
                } else {
                    // 出栈计算数据 知道没有操作符，or 优先级高
                    while (!isStackTopHight(chars[i], optStack)) {
                        cal(numberStack, optStack);
                    }
                    optStack.push(chars[i]);
                }
                if (chars[i] != ')')
                    lastIsOpt = true;
            }
        }
        if (numBuilder.length() > 0) {
            numberStack.push(Integer.valueOf(numBuilder.toString()));
        }
        // 计算剩下的操作
        while (numberStack.size() > 1) {
            cal(numberStack, optStack);
        }
        System.out.println(numberStack.pop());
    }

    public static boolean isStackTopHight(char opt, LinkedList<Character> optStack) {
        if (optStack.size() == 0)
            return true;
        return optVal[opt] > optVal[optStack.getFirst()];
    }

    public static void cal(LinkedList<Integer> numStack, LinkedList<Character> optStack) {
        int num2 = numStack.pop();
        int num1 = numStack.pop();
        Character opt = optStack.pop();
        int result = 0;
        if (opt == '+') {
            result = num1 + num2;
        } else if (opt == '-') {
            result = num1 - num2;
        } else if (opt == '*') {
            result = num1 * num2;
        } else {
            result = num1 / num2;
        }
        numStack.push(result);
    }

    public static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
}
