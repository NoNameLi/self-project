package cn.peng.studygodpath.java8.collection;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-21 11:06
 * QDescription:
 */
public class StreamPlay {

    @Test
    public void testStreamIterate() {
//        Stream.iterate(0, i -> i + 1).filter(i -> i < 10).forEach(i -> System.out.print(i));
        System.out.print("\n");
        Stream.iterate(0, i -> i + 1).limit(10).forEach(i -> System.out.print(i));

    }
}
