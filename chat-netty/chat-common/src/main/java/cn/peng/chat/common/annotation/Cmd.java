package cn.peng.chat.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cmd {

    short value() default 0;

    boolean needLogin() default true;
}
