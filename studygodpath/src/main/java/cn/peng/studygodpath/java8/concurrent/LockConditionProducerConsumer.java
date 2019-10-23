package cn.peng.studygodpath.java8.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionProducerConsumer {

    LinkedList<String> store = null;
    int maxSize = 0;

    private ReentrantLock lock = null;
    private Condition fullCondition = null;
    private Condition noFullCondition = null;

    public LockConditionProducerConsumer(int size) {
        maxSize = size;
        store = new LinkedList<>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        noFullCondition = lock.newCondition();
    }

    public void set(String content) {
        lock.lock();
        try {
            if (maxSize <= store.size()) {
                noFullCondition.await();// 将当前线程处于等待状态，直到结束到信号和线程中断
//                noFullCondition.awaitUninterruptibly(); // 同上 但对中断不敏感
            }
            store.add(content);
            fullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public String get() {
        lock.lock();
        try {
            if (store.size() == 0) {
                fullCondition.await();
            }
            String poll = store.poll();
            noFullCondition.signal();
            return poll;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }

    }


}
