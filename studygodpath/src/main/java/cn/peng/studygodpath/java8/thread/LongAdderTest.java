package cn.peng.studygodpath.java8.thread;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder 采用分而治之方式策略来减少同一个变量的并发竞争度，核心思想是把一个原子变量分解为多个变量，让同样多的线程去竞争多个资源，这样竞争每个资源的线程数就被分担了下来
 * 内部维护了一个 Cell数组，当多个线程在争夺同一个Cell原子变量时候如果失败并不是在当前cell变量上一直自旋CAS重试，而是会尝试在其它Cell的变量上进行CAS尝试，
 * 这个改变增加了当前线程重试时候CAS成功的可能性。最后获取LongAdder当前值的时候是把所有Cell变量的value值累加后在加上base返回的
 *
 * Random 内部维护了一个 原子性的种子变量（AtomicLong），在每次生成随机数，都会根据老的种子值计算新的种子值并使用cas原理更新种子变量，因此会有cas的缺点。
 *  在竞争冲突压力大的时候，会有大量的线程失败自旋重试。而大量的自旋重试会降低并发和消耗cpu资源
 *
 * ThreadLocalRandom 就是为了解决 Random的问题,原理 类似 ThreadLocal,每个线程有个副本
 */
public class LongAdderTest {

    public static void main(String[] args) {

        LongAdder longAdder = new LongAdder();
        longAdder.add(1L);

        Random random = new Random();
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        localRandom.nextInt();
        localRandom.nextInt();
        ThreadLocal threadLocal = new ThreadLocal();

    }
}
