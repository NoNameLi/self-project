package cn.peng.studygodpath.design.behavior.chain;

import java.util.List;
import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-17 17:58
 * QDescription:
 */
public interface PriceChain {

    void addProcess(Processor filter);

    List<Processor> listProcess();

    void process(Map<String, Object> context);

    void release();

}
