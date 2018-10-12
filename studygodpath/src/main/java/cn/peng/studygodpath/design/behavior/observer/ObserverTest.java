package cn.peng.studygodpath.design.behavior.observer;

/**
 * 观察者模式
 * 
 * 有四个角色 主题的抽象接口 subject 职能 把所有的观察者的引用保存起来，所以就会有 增加 和删除观察者的方法 主题的具体实现
 * 
 * 观察者的接口 observer 在主题发生变化时，需要更新观察者 ，就需要 观察者有一个 得到主题的通知更新自己的方法 观察者的具体实现
 * 
 * 
 * java jdk 已经有实现好的观察者模式 
 * java.util.Observable 类 
 * java.util.Observer   接口
 * 基本代码 类似 
 */

public class ObserverTest {

    public static void main(String[] args) {
        WeChatServer server = new WeChatServer();
        User one = new User("one");
        User two = new User("two");
        User three = new User("three");

        server.registerObserver(one);
        server.registerObserver(two);
        server.registerObserver(three);

        server.nodifyObserver("java is good");
        server.registerObserver(one);
        server.removeObserver(three);
        server.nodifyObserver("java is very good");

    }
}