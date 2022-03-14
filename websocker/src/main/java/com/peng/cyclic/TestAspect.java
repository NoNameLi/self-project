package com.peng.cyclic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @CreateTime:2022-03-11 15:19
 * QDescription:
 */
@Aspect
@Component
public class TestAspect {


    @Around("execution(public * com.peng.cyclic.bean.Cyclic.*(..))")
    public void test(ProceedingJoinPoint point) {
        System.out.println("......befor");
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("......after");

    }
}
