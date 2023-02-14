package cn.peng.studygodpath.frame.disruptor.demo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LongEvent {

    private Long value;

    public void clear() {
        this.value = null;
    }

}
