package cn.peng.chat.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Model {
    short value() default 0;

    boolean needLogin() default true;
}
