/**
 * 策略模式
 * 
 * 同一件事 不同的情况有不同的策略算法执行
 * 
 * 实际上是将变化封装起来 设计模式应该都是用不同的方式将变化封装
 * 
 * 有三个角色：
 * content              环境角色（stragety 的持有者）or 策略工厂
 * stragety interface   定义的策略接口
 * stragety Instance    实现策略的具体策略实现
 *
 *
 * 再次抽象：增加泛型 统策略一模板
 * @author remote
 *
 */
package cn.peng.studygodpath.design.behavior.strategy;