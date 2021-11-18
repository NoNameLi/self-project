package cn.peng.studygodpath.java8.defaultmethod;

import org.testng.annotations.Test;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-17 23:30
 * QDescription:
 */
public class TestStaticChilder extends TestStaticOverride {

    public static void interfaceStatic(){
        System.out.println("this is childer static method");
    }

    @Test
    public void objectStaticMethod() {
        TestStaticOverride.interfaceStatic();
        TestStaticChilder.interfaceStatic();
        new TestStaticOverride().interfaceStatic();
        new TestStaticChilder().interfaceStatic();
        Default.interfaceStatisMethod();
        Default defaultInstall = new DefaultMethod();
        // 对象类的静态方法，可以本类，调用，本类实例 子类实例调用
        // 接口的静态方法，只能通过接口类调用
//        defaultInstall.interfaceStatisMethod();//报错


    }
}
