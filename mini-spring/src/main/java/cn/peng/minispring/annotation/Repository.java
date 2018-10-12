package cn.peng.minispring.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)// 注解的作用范围 类
@Retention(RetentionPolicy.RUNTIME) // 注解的生命周期 运行期间
@Component
public @interface Repository {
    public String value() default "";
}
