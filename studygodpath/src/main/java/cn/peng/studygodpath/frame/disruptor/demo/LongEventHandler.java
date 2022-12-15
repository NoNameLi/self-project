package cn.peng.studygodpath.frame.disruptor.demo;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName() + ":" + longEvent.toString());
    }
}
