package cn.peng.studygodpath.design.behavior.chain;

import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-18 09:20
 * QDescription:
 */
public class DefaultProcessor implements BusinessProcessor {
    @Override
    public void doFilter(Map<String, Object> context, BusinessChain chain) {
        // do next FilterHandler
        chain.doFilter(context);
    }
}
