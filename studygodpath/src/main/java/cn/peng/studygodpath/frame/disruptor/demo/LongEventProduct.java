package cn.peng.studygodpath.frame.disruptor.demo;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProduct {

    private final RingBuffer<LongEvent> ringBuffer;

    private final EventTranslatorOneArg<LongEvent, Long> translate = (event, sequence, data) -> event.setValue(data);

    public LongEventProduct(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Long data) {
        long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(data);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public void onDataByTranslator(Long data) {
        ringBuffer.publishEvent(translate, data);

    }
}
