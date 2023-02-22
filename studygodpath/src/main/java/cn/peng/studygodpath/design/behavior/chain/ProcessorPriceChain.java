package cn.peng.studygodpath.design.behavior.chain;

import java.util.*;

/**
 * 非线程安全设计
 */
public class ProcessorPriceChain implements PriceChain {

    private List<Processor> processors;

    Iterator<Processor> iterator = null;


    public ProcessorPriceChain() {
        this.processors = new ArrayList<>();
    }

    @Override
    public void addProcess(Processor filter) {
        this.processors.add(filter);
    }

    @Override
    public List<Processor> listProcess() {
        return this.processors;
    }

    @Override
    public void process(Map<String, Object> context) {
        if (Objects.isNull(iterator)) {
            iterator = this.processors.iterator();
        }

        if (iterator.hasNext()) {
            iterator.next().process(context, this);
        }
    }

    @Override
    public void release() {
        iterator = null;
    }
}
