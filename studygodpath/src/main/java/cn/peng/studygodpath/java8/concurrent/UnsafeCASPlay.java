package cn.peng.studygodpath.java8.concurrent;

import org.testng.annotations.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Unsafe 类 方式理解使用
 * <p>
 * Cas 操作 实际上就是利用 Unsafe 实现的
 * <p>
 * 缺点 不足：
 * 1. 一次仅能满足一个变量的原子操作，多变量就要用锁 or
 * 2. 比较失败，就会循环尝试重新比较赋值，有可能出现多次循环，造成cup性能浪费
 * 3. 不能避免 aba 现象  可以使用 AtomicStampedReference 来解决 样例见下
 */
public class UnsafeCASPlay {
    // 非 rj.jar 调用 会包异常 SecurityException 因为 Unsafe 内部限制 只有调用的类是由 启动类加载器(BootStrap ClassLoader) 加载的才能成功
//    Unsafe unsafe = Unsafe.getUnsafe();
    Unsafe unsafe = null;
    private int value = 0;

    private long valueOffset;

    public UnsafeCASPlay() {
        Field theUnsafeInstance = null;
        try {
            theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeInstance.setAccessible(true);
            unsafe = (Unsafe) theUnsafeInstance.get(Unsafe.class);
            //获取属性在类对象的内存首地址的偏移量 从而定位到 属性的位置
//            valueOffset = unsafe.objectFieldOffset(UnsafeCASPlay.class.getField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void run() {
    }


    public void test1() {
        // 第一个参数 是 要赋值的对象
        // 第二个参数 是 具体值在对象的内存地址偏移量 可以通过 unsage.objectFieldOffset 获取
        // 第三个参数 是 赋值的期望值
        // 第四个参数 是 要变的值
        // JDK 使用 具体可以参考 基本类型数据的支持原则操作类  AtomicInteger
        AtomicInteger test = new AtomicInteger();
//        AtomicStampedReference
        test.addAndGet(5);
        unsafe.compareAndSwapInt(this, valueOffset, value, 0);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casABA() {
        AtomicInteger ai = new AtomicInteger(100);
        AtomicStampedReference<Integer> air = new AtomicStampedReference<>(100, 1);

        Thread one = new Thread(() -> {
            ai.compareAndSet(100, 110);
            ai.compareAndSet(110, 100);
        });
        Thread two = new Thread(() -> {
            TimeUnit.SECONDS.toSeconds(2);// 为了one执行
            System.out.println(ai.compareAndSet(100, 120));
        });
        one.start();
        two.start();


        Thread three = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            air.compareAndSet(100, 110, air.getStamp(), air.getStamp() + 1);
            air.compareAndSet(110, 100, air.getStamp(), air.getStamp() + 1);
        });

        Thread four = new Thread(() -> {
            int stamp = air.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(air.compareAndSet(100, 120, stamp, stamp + 1));
        });
        three.start();
        four.start();
        try {
            one.join();
            two.join();
            three.join();
            four.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
