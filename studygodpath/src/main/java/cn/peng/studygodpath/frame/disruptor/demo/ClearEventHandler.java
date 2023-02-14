package cn.peng.studygodpath.frame.disruptor.demo;


import com.lmax.disruptor.EventHandler;

public class ClearEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.clear();
    }
}
