package cn.peng.studygodpath.java8.math;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * MathPlay java 中数学计算的方式
 */
public class MathPlay {

    @Test
    public void testRound(){
        System.out.println(Math.round(1.56));
        System.out.println(Math.round(1.46));
        System.out.println(Math.round(-1.51));
        System.out.println(Math.round(-1.46));


    }
}
