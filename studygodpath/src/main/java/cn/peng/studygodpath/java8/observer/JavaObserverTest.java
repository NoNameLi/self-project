package cn.peng.studygodpath.java8.observer;

import java.util.Observer;

import org.junit.Test;

/**
 * 使用java 提供的实现 观察者模式
 * 
 * @author remote
 *
 */
public class JavaObserverTest {

    @Test
    public void test() {
        Subject subject = new Subject();
        Observer o = new JavaObserver(null);
        Observer o1 = new JavaObserver(null);
        subject.addObserver(o);
        subject.addObserver(o1);
        subject.setData(5);
        subject.deleteObserver(o1);
        subject.setData(10);
    }

    @Test
    public void flagTest() {

        Subject subject = new Subject();
        Observer o = new JavaObserver(Subject.EVEN);
        Observer o1 = new JavaObserver(Subject.ODD);
        subject.addObserver(o);
        subject.addObserver(o1);
        subject.setData(5);
        subject.setData(10);
    }
}
