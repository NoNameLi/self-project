package cn.peng.studygodpath.java8.sourcecode;

import org.testng.annotations.Test;

/**
 * try catch final 对程序执行的影响
 */
public class ThrowsTest {

    enum ReturnPoint {
        normal_return, catch_return, finally_return;
    }

    @Test
    public void testNormal() {
        // 1
        System.out.println(process(4, 2, ReturnPoint.normal_return));
        // 3
        System.out.println(process(4, 2, ReturnPoint.finally_return));
    }

    @Test
    public void testException() {
        // 3
        System.out.println(process(2, 0, ReturnPoint.normal_return));
        // 2
        System.out.println(process(2, 0, ReturnPoint.catch_return));
        // 3
        System.out.println(process(2, 0, ReturnPoint.finally_return));

    }


    public int process(int n, int m, ReturnPoint returnPoint) {
        int a = 0;
        try {
            n = n / m;
            a = 1;
            if (ReturnPoint.normal_return == returnPoint)
                return a;
        } catch (Exception e) {
            a = 2;
            if (ReturnPoint.catch_return == returnPoint)
                return a;
        } finally {
            a = 3;
            if (ReturnPoint.finally_return == returnPoint)
                return a;
        }
        return a;
    }

}
