package cn.peng.studygodpath.java8.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @Author: Administrator
 * @CreateTime:2022-08-24 14:39
 * QDescription:
 */
public class PropertyChangeTest {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private String EVENT_NAME = "TEST";
    private String EVENT_NAME2 = "TEST2";

    public static void main(String[] args) {
        PropertyChangeTest eventSource = new PropertyChangeTest();
        PropertyChangeListener listener = (event) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event.toString());
        };
        eventSource.propertyChangeSupport.addPropertyChangeListener(listener);
        eventSource.propertyChangeSupport.addPropertyChangeListener(eventSource.EVENT_NAME, listener);
        eventSource.propertyChangeSupport.firePropertyChange(eventSource.EVENT_NAME, null, "值对象");
        eventSource.propertyChangeSupport.firePropertyChange(eventSource.EVENT_NAME2, null, "值对象");

    }

}
