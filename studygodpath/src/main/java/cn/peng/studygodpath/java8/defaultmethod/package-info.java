/**
 * java8 赋予接口（interface）新的功能：默认方法 和静态函数
 *  可以在接口中实现接口共通的方法，实现接口者也可以重写默认方法
 *  接口中可以定义静态方法，只能通过此类调用
 *
 * 实现者实现两个含有相同的方法的接口时，实现者必须重写此方法。否则会保存，跟C 、C++ 一样
 *
 *
 * 当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。
 *
 * 子类父类方法调用级别
 * this.show(O) super.show(O) this.show((super)O) supor.show((supor)O)
 *
 * Created by remote on 2017/7/7.
 */
package cn.peng.studygodpath.java8.defaultmethod;