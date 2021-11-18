package cn.peng.studygodpath.java8.gc;

import org.testng.annotations.Test;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-17 17:58
 * QDescription:
 */
public class MemoryTest {


    @Test
    public void printMemory(){
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
    }
}
