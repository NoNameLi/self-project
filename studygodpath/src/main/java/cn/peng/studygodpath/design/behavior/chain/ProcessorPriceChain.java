package cn.peng.studygodpath.design.behavior.chain;

import java.util.*;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-18 09:16
 * QDescription:
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

    }
}
