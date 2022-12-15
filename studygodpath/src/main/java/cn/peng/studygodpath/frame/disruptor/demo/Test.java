package cn.peng.studygodpath.frame.disruptor.demo;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        // 创建工厂
        LongEventFactory factory = new LongEventFactory();
        // 创建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, 1024, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new YieldingWaitStrategy());
        disruptor.handleEventsWith((LongEvent longEvent, long l, boolean b) -> {
            System.out.println("1:" + longEvent.toString());
        });
        disruptor.handleEventsWith((LongEvent longEvent, long l, boolean b) -> {
            System.out.println("2:" + longEvent.toString());
        });
        disruptor.start();
        LongEventProduct product = new LongEventProduct(disruptor.getRingBuffer());
        for (int i = 0; i < 10; i++) {
            product.onData((long) i);
            Thread.sleep(500);
        }
//        disruptor.shutdown();
    }
}
