package cn.peng.studygodpath.spring4.aop;

import cn.peng.studygodpath.spring4.aop.service.component.ParamCheck;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * service 层 切面
 */
@Aspect
@Component
public class ServiceAop {

    @Autowired
    private ParamCheck paramCheck;

    @Around("execution(public * cn.peng.studygodpath.spring4.aop.service.impl..*.*(..))")
    public Object simpleAop(ProceedingJoinPoint point) {
        System.out.println("切面开始");
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("切面结束");
        return result;
    }

    @Around("execution(public * cn.peng.studygodpath.spring4.aop.service.impl..*.*(..))")
    public Object checkAop(ProceedingJoinPoint point) {
        System.out.println("校验开始");
        Object result = null;
        Object[] args = point.getArgs();
        if (paramCheck.check(args)) {
            try {
                result = point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        System.out.println("校验结束");
        return result;
    }

}
