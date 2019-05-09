package cn.peng.studygodpath.java8;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 递归例子
 */
public class RecursionExample {

    @Test
    public void run() {
        String[] data = {"1", "2"};
        System.out.println(this.arrayElementJoin(Arrays.asList(data), ""));

        System.out.println(this.fibonacciNum(6));
    }

    /**
     * 组合集合中的元素所有情况
     *
     * @param data
     * @param prefix
     */
    public ArrayList<String> arrayElementJoin(List<String> data, String prefix) {
        ArrayList<String> list = new ArrayList<>();
        if (StringUtils.isNoneBlank(prefix)) {
            list.add(prefix);
        }
        for (int i = 0; i < data.size(); i++) {
            LinkedList<String> temp = new LinkedList<>(data);
            list.addAll(arrayElementJoin(temp, prefix + temp.remove(i)));
        }
        return list;
    }

    /**
     * 输出n 个斐波那契数列
     * 0 1 1 2 3 5 8 13
     *
     * @param n
     */
    public int fibonacciNum(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacciNum(n - 1) + fibonacciNum(n - 2);
    }

}
