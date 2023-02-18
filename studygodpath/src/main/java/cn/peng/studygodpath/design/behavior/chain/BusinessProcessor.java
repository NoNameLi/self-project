package cn.peng.studygodpath.design.behavior.chain;

import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-18 09:14
 * QDescription:
 */
public interface BusinessProcessor {

    void doFilter(Map<String,Object> context, BusinessChain chain);

}
