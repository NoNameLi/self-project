package cn.peng.studygodpath.java8.sourcecode.map;

import java.io.Serializable;

/**
 * 手写HashMap
 * <p>
 * 数据保存 ：数组
 * hash函数：长度取余
 * hash冲突：拉链法
 */
public class MyMap<K, V> implements Serializable {

    private MyNode[] array;

    private int size;

    private int capacity;

    class MyNode<K, V> {
        private K key;
        private V value;
        private MyNode<K, V> next;
    }

    private int hashIndex(K key) {
        return key.hashCode() & (array.length - 1);
    }

    public MyMap() {
        this(10);
    }

    public MyMap(int capacity) {
        this.array = new MyNode[capacity];
    }

    public MyMap<K, V> put(K key, V v) {
        return this;
    }

    public MyMap<K, V> putAll(MyMap<K, V> map) {
        return this;
    }

    public V get(K key) {

        return null;
    }

    public V remove(K key) {
        return null;
    }
}
