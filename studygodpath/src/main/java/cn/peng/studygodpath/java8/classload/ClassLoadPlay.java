package cn.peng.studygodpath.java8.classload;

public class ClassLoadPlay {


    public static void main(String[] args) {

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        ClassLoader classLoader = Thread.currentThread().getClass().getClassLoader();
        System.out.println(classLoader);
    }
}
