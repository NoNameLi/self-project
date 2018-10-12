package cn.peng.studygodpath.java8.annotion;

import java.lang.annotation.*;
import java.util.Arrays;

/**
 * Created by remote on 2018/3/2.
 *
 * Inherit 带有此注解的注解可以传递的子类上
 * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 *
 * 注解和普通类的区别是如果一个子类想获取到父类上的注解信息，那么必须在父类上使用的注解上面 加上@Inherit关键字
 */
public class InheritTest {

    public static void main(String[] args) {
        Class<Sub> clazz = Sub.class;
        System.out.println("Sub is have BTable annotion？" + clazz.isAnnotationPresent(BTable.class));
        System.out.println("Sub have annotion:" + Arrays.toString(clazz.getAnnotations()));
    }


}


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface ATable {

    public String name() default "";
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface BTable {
    public String name() default "";
}


@ATable
class Super {
    private int superx;
    public int supery;
    public Super() {
    }
    private int superX(){
        return 0;
    }
    public int superY(){
        return 0;
    }

}


@BTable
class Sub extends Super{
    private int subx;
    public int suby;
    public Sub()
    {
    }
    public Sub(int i){
    }
    private int subX(){
        return 0;
    }
    public int subY(){
        return 0;
    }
}

