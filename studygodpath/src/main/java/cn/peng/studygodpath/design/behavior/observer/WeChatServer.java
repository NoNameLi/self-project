package cn.peng.studygodpath.design.behavior.observer;

import java.util.Set;

import com.google.common.collect.Sets;

public class WeChatServer implements Observerable {

    /** 将观察者的引用保存在内存中，实际应用中这样回很不安全，应该将 关注关系 保存到数据库（redis）中 */
    private Set<Observer> observerSet = Sets.newHashSet();

    @Override
    public Set<Observer> getObserverList() {
        return this.observerSet;
    }

    @Override
    public void registerObserver(Observer observer) {
        observerSet.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observerSet.contains(observer)) {
            observerSet.remove(observer);
        }
    }

}
