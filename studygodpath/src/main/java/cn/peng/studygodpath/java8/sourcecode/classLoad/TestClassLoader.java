package cn.peng.studygodpath.java8.sourcecode.classLoad;

import cn.peng.studygodpath.java8.sourcecode.classLoad.custom.DiskClassLoader;
import cn.peng.studygodpath.java8.sourcecode.classLoad.loadSquac.Children;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestClassLoader {


    @Test
    public void testReferenceLoad() {// 测试引用导致加载的情况
        // 直接引用
        // new
        // 调用静态变量、静态方法
        // 反射
        // 限定名
        // 间接引用
        // 调用静态变量、静态方法，值加载声明所属的类，如下，值会加载父类
//        String sv = Children.sv;
        // 数组不会加载数组的元素类。数组类是jvm动态生成的另一个类
//        Children[] childrens = new Children[10];
        // 基本类型的静态常量 不会引起任何类的加载
        int max = Children.MAX;
    }


    @Test
    public void testClassLoadSquac() {
        System.out.println("class 加载顺序：");
        Class clazz = Children.class;
        System.out.println(Children.ss2);
        System.out.println("实例化顺序：");
        new Children();
    }

    @Test
    public void testSystemClassLoaderPath() {
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println(System.getProperty("java.class.path"));
//        System.out.println(System.getProperty("java.ext.dirs"));
//        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.security.manager"));
    }

    @Test
    public void getAllClassLoader() {
        ClassLoader loader = TestClassLoader.class.getClassLoader();
        while (null != loader) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }

    @Test
    public void testClustomClassLoader() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir")).append(File.separator).append("src").append(File.separator).append("main")
                .append(File.separator).append("java").append(File.separator);

        DiskClassLoader diskClassLoader = new DiskClassLoader(sb.toString());
        try {
            Class<?> aClass = diskClassLoader.loadClass("cn.peng.studygodpath.java8.sourcecode.classLoad.custom.TestCustomLoaderClass");
            Object instance = aClass.newInstance();
            Method hello = aClass.getDeclaredMethod("hello", null);
            hello.invoke(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
