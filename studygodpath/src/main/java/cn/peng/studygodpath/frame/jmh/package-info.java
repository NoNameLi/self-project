/**
 * JMH (java Microbenchmark Harness)  专门用于代码微基准测试的工具套件
 * 
 * 基本概念：
 * Mode:mode 表示 JMH 进行Bennchmark(基准)时所使用的模式，通常是测量的维度不同，
    Throughput: 整体吞吐量，例如“1秒内可以执行多少次调用”。
    AverageTime: 调用的平均时间，例如“每次调用平均耗时xxx毫秒”。
    SampleTime: 随机取样，最后输出取样结果的分布，例如“99%的调用在xxx毫秒以内，99.99%的调用在xxx毫秒以内”
    SingleShotTime: 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup 次数设为0，用于测试冷启动时的性能。
 *  
 * Iteration 
    Iteration 是 JMH 进行测试的最小单位。在大部分模式下，一次 iteration 代表的是一秒，JMH 会在这一秒内不断调用需要 benchmark 的方法，然后根据模式对其采样，计算吞吐量，计算平均执行时间等。
 * Warmup
    Warmup 是指在实际进行 benchmark 前先进行预热的行为。为什么需要预热？因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度。
           为了让 benchmark 的结果更加接近真实情况就需要进行预热
 * 
 * 注解：
 * 
 * @BenchmarkMode 
            对应Mode选项，可用于类或者方法上， 需要注意的是，这个注解的value是一个数组，可以把几种Mode集合在一起执行，还可以设置为Mode.All，即全部执行一遍。
 *
 * @State
            类注解，JMH测试类必须使用@State注解，State定义了一个类实例的生命周期，可以类比Spring Bean的Scope。由于JMH允许多线程同时执行测试，不同的选项含义如下：
        Scope.Thread：默认的State，每个测试线程分配一个实例；
        Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
        Scope.Group：每个线程组共享一个实例；
 *
 *@OutputTimeUnit 
    benchmark 结果所使用的时间单位，可用于类或者方法注解，使用java.util.concurrent.TimeUnit中的标准时间单位。
 *
 *@Benchmark 
          方法注解，表示该方法是需要进行 benchmark 的对象。
 *
 *@Setup 
                方法注解，会在执行 benchmark 之前被执行，正如其名，主要用于初始化。
 *
 *@TearDown 
                方法注解，与@Setup 相对的，会在所有 benchmark 执行结束以后执行，主要用于资源的回收等
 *
 *@Param 
                成员注解，可以用来指定某项参数的多种情况。特别适合用来测试一个函数在不同的参数输入的情况下的性能。@Param注解接收一个String数组，
                在@setup方法执行前转化为为对应的数据类型。多个@Param注解的成员之间是乘积关系，譬如有两个用@Param注解的字段，第一个有5个值，第二个字段有2个值，
                那么每个测试方法会跑5*2=10次。
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package cn.peng.studygodpath.frame.jmh;