package cn.peng.studygodpath.design.behavior.chain;

import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-18 09:14
 * QDescription:
 */
public interface Processor {

    void process(Map<String,Object> context, PriceChain chain);

}
