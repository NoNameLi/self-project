package cn.practice.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 11:21
 * QDescription:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {

    @Test
    public void hello(){
        System.out.println("unit test run");
    }
}
