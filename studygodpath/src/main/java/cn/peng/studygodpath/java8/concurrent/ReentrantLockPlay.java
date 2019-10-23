package cn.peng.studygodpath.java8.concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock 重入锁  有非公平锁 和公平锁的选择，构造参数 为 true 为 公平锁，公平锁是指 有顺序的获取锁
 */
public class ReentrantLockPlay {

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
