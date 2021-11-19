package cn.peng.studygodpath.java8.gc;

import org.junit.Test;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-10 17:00
 * QDescription:
 */
public class GCTest {

    /**
     * -verbose:gc
     * -Xmx200M
     * -Xms200M
     * -Xmn50M
     * -XX:+PrintGCDetails
     * -XX:TargetSurvivorRatio=60 幸存区目标使用率 影响 晋升老年区的年龄
     * -XX:+PrintTenuringDistribution 幸存区 gc的年龄分布
     * -XX:+PrintGCDetails gc明细
     * -XX:+PrintGCDateStamps gc的时间戳
     * -XX:MaxTenuringThreshold=3 晋升老年代的最大年龄
     * -XX:+UseConcMarkSweepGC 使用cms回收器
     * -XX:+UseParNewGC
     */
    public static void main(String[] args) {
        makeGarbage(2);
        makeGarbage(1);
        byte[] byte1m_1 = new byte[5 * 128 * 1024];// 此时eden 可以分配 ，都在 eden代，2M + 1M + 0.625M + 空方法占用的内存
        byte1m_1 = null;
        byte[] byte1m_2 = new byte[1024 * 1000];// 此时eden不够分配，进行minor gc  2m + 1m 没有引用回收，byte1m_2 进入 from代
//        byte[] byte1m_3 = new byte[1 * 1024 * 1024];
//        makeGarbage(1);
//        System.gc();
    }

    /*
     * 本实例用于java GC以后，新生代survivor区域的变化，以及晋升到老年代的时间和方式的测试代码。需要自行分步注释不需要的代码进行反复测试对比
     *
     * 由于java的main函数以及其他基础服务也会占用一些eden空间，所以要提前空跑一次main函数，来看看这部分占用。
     *
     * 自定义的代码中，我们使用堆内分配数组和栈内分配数组的方式来分别模拟不可被GC的和可被GC的资源。
     * */
    @Test
    public void testGC() {
        //空跑一次main函数来查看java服务本身占用的空间大小，我这里是占用了3M。所以40-3=37，下面分配三个1M的数组和一个34M的垃圾数组。
        // 为了达到TargetSurvivorRatio（期望占用的Survivor区域的大小）这个比例指定的值, 即5M*60%=3M(Desired survivor size)，
        // 这里用1M的数组的分配来达到Desired survivor size
        //说明: 5M为S区的From或To的大小，60%为TargetSurvivorRatio参数指定,可以更改参数获取不同的效果。
        byte[] byte1m_1 = new byte[1 * 1024 * 1024];
        byte[] byte1m_2 = new byte[1 * 1024 * 1024];
        byte[] byte1m_3 = new byte[1 * 1024 * 1024];

        //使用函数方式来申请空间，函数运行完毕以后，就会变成垃圾等待回收。此时应保证eden的区域占用达到100%。可以通过调整传入值来达到效果。
        makeGarbage(34);

        //再次申请一个数组，因为eden已经满了，所以这里会触发Minor GC
        byte[] byteArr = new byte[10 * 1024 * 1024];
        // 这次Minor Gc时, 三个1M的数组因为尚有引用，所以进入From区域（因为是第一次GC）age为1
        // 且由于From区已经占用达到了60%(-XX:TargetSurvivorRatio=60), 所以会重新计算对象晋升的age。
        // 计算方法见上文，计算出age：min(age, MaxTenuringThreshold) = 1，输出中会有Desired survivor size 3145728 bytes, new threshold 1 (max 3)字样
        //新的数组byteArr进入eden区域。


        //再次触发垃圾回收，证明三个1M的数组会因为其第二次回收后age为2，大于上一次计算出的new threshold 1，所以进入老年代。
        //而byteArr因为超过survivor的单个区域，直接进入了老年代。
        makeGarbage(34);
    }

    private static void makeGarbage(int size) {
        byte[] byteArrTemp = new byte[size * 1024 * 1024];
    }

    @Test
    public void testBigObject() {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900 * 1024]; // 对象直接进入老年的
        //allocation2 = new byte[900*1024];
    }

}
