/**
 * Created by remote on 2017/7/10.
 *
 * 创建型设计模式：
     * 工厂方法模式
     * 抽象工厂模式
     * 单例模式
     * 
     * 建造者模式 可以参考 lombok @Builder注解
     * 原型模式 ： 原型模式就是用原型实例指定创建对象的种类，并且通过复制这些原型创建新的对象。该模式就是实习深度拷贝功能
 *
 * 单例问题注重点：
     1、如果单例由不同的类装载器装入，那便有可能存在多个单例类的实例。假定不是远端存取，例如一些servlet容器对每个servlet使用完全不同的类  装载器，这样的话如果有两个servlet访问一个单例类，它们就都会有各自的实例。
     2、如果Singleton实现了java.io.Serializable接口，那么这个类的实例就可能被序列化和复原。不管怎样，如果你序列化一个单例类的对象，接下来复原多个那个对象，那你就会有多个单例类的实例。
        第二个可以
 *
 */
package cn.peng.studygodpath.design.build;