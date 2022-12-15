package cn.peng.studygodpath.frame.disruptor.demo;

import com.lmax.disruptor.RingBuffer;

public class LongEventProduct {

    private RingBuffer<LongEvent> ringBuffer;

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
}
