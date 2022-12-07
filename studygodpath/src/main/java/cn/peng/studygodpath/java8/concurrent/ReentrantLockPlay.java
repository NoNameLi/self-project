package cn.peng.studygodpath.java8.concurrent;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock 重入锁  有非公平锁 和公平锁的选择，构造参数 为 true 为 公平锁，公平锁是指 有顺序的获取锁
 */
public class ReentrantLockPlay {
    public static ReentrantLock globalLock = new ReentrantLock();

    volatile Map<String, Object> cache = new HashMap<>();

    @Test
    public void readWriteLockTest() throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Thread write = new Thread(() -> {
            writeLock.lock();
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cache.put("" + i, i);
                System.out.println("写入" + i + ":" + cache.get("" + i));
            }
            writeLock.unlock();
        });

        Thread read = new Thread(() -> {
            readLock.lock();
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("读取" + i + ":" + cache.get("" + i));
            }
            readLock.unlock();
        });
        write.start();
        read.start();
        write.join();
        read.join();


    }

    @Test
    public void reentrantLockTest() {
        globalLock.lock();
        get();
        globalLock.unlock();
    }

    public void get() {
        globalLock.lock();
        System.out.println("get methond exec.....");
        globalLock.unlock();
    }

    public void test1() {
        ReentrantLock lock = new ReentrantLock(false);//new ReentrantLock(true);
        try {
            lock.lock();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }


        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        Condition condition = readLock.newCondition();// throws UnsupportedOperationException
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition.signal();
        readLock.unlock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        writeLock.unlock();
        readWriteLock.getReadLockCount();

    }

}
