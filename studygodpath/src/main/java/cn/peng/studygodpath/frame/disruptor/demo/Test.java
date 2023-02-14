package cn.peng.studygodpath.frame.disruptor.demo;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class Test {
    public static void main(String[] args) {
        // 创建工厂
        LongEventFactory factory = new LongEventFactory();
        // 创建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, 16, DaemonThreadFactory.INSTANCE
                , ProducerType.MULTI, new YieldingWaitStrategy());

        //处理完事件清除，
        EventHandler<LongEvent> clearEventHandler = (LongEvent longEvent, long l, boolean b) -> longEvent.clear();

        EventHandlerGroup<LongEvent> group = disruptor
                .handleEventsWith((LongEvent longEvent, long l, boolean b) -> System.out.println("1:" + longEvent.toString()))
                .then(clearEventHandler);

        EventHandlerGroup<LongEvent> group1 = disruptor
                .handleEventsWith((LongEvent longEvent, long l, boolean b) -> System.out.println("2:" + longEvent.toString()))
                .then(clearEventHandler);

        disruptor.start();

        LongEventProduct product = new LongEventProduct(disruptor.getRingBuffer());
        for (int i = 0; i < 1000; i++) {
            product.onData((long) i);
            disruptor.getRingBuffer().publishEvent((event, sequence, data) -> event.setValue(data), (long) i);
            System.out.println("生产:" + i);
        }
//        disruptor.shutdown();
    }
}
