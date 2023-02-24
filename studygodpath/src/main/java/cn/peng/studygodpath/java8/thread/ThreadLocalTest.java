package cn.peng.studygodpath.java8.thread;

import java.lang.ref.WeakReference;
import java.util.stream.Stream;

/**
 * 内存泄露 原因：
 * 使用ThreadLocal
 * put方法，会以 threadLocal对象为key 参数为值 存入当前线程的 threadLocalMaps 中，其中 key是以 弱引用引用的，
 * 这样 threadLocal对象 会有两个引用，即：new 时的引用，和 threadLocalMaps里的key 弱引用，
 * 当将new引用置为空时，jvm会将 threadLocalMaps 中的key回收，这样当线程销毁前，对应的值就不能被访问 不能垃圾回收
 *
 * 解决：
 *  1.使用 remove 方法 也可以 set get 方法会清除无用entity
 *  2.线程销毁
 */
public class ThreadLocalTest {


    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        Stream.iterate(0, i -> ++i).limit(100).forEach(threadLocal::set);
        threadLocal = null;
        System.gc();
        Thread thread = Thread.currentThread();
        WeakReference<Long> reference = new WeakReference<>(0L);
    }


}
