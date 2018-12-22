/**
 * RunTime 是一个与JVM 运行时环境有关的类
 *
 *   Runtime.getRuntime().exec(String[]) JVM 执行外部程序命令
     Runtime.getRuntime().exit(int); 退出当前JVM的 ，0代表正常退出，非0代表异常中止 并启动JVM 的关闭序列
     Runtime.getRuntime().addShutdownHook(Thread); 注册新的虚拟机关闭挂钩。
     Runtime.getRuntime().removeShutdownHook() 取消注册某个先前已注册的虚拟机关闭挂钩。
     Runtime.getRuntime().halt() 强行终止目前正在运行的 Java 虚拟机。
     Runtime.getRuntime().availableProcessors() 获取jvm可用的处理器核心的数量
     Runtime.getRuntime().gc();运行垃圾回收器
     Runtime.getRuntime().freeMemory() 可用空闲内存 单位字节
     Runtime.getRuntime().maxMemory() 最大可使用内存
     Runtime.getRuntime().totalMemory(); 总内存
     Runtime.getRuntime().loadLibrary(); 加载具有指定库名的动态库
     Runtime.getRuntime().load(File); 加载作为动态库的指定文件名。
     Runtime.getRuntime().runFinalization(); 运行挂起 finalization 的所有对象的终止方法。
 */
package cn.peng.studygodpath.java8.runtime;