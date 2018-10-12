package cn.peng.studygodpath.design.behavior.observer;

import java.util.Set;

/*
 * 定义被观察者接口
 */
public interface Observerable {

    /**
     * 获取 关注的观察者
     * 
     * @return
     */
    Set<Observer> getObserverList();

    /**
     * 观察者关注
     * 
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 取消关注
     * 
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    default void nodifyObserver(Object message) {
        Set<Observer> observerList = getObserverList();
        observerList.forEach(item -> {
            item.update(message);
        });
    }

}
