package cn.peng.studygodpath.design.behavior.chain;

import java.util.List;
import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-17 17:58
 * QDescription:
 */
public interface BusinessChain {

    void addFilter(BusinessProcessor filter);

    List<BusinessProcessor> listFilter();

    void doFilter(Map<String, Object> context);

    void release();

}
