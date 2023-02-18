package cn.peng.studygodpath.design.behavior.chain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-18 11:41
 * QDescription:
 */
public class Test {
    public static void main(String[] args) {

        execAllFilter();
    }


    public static void execAllFilter() {
        BusinessChainImpl chain = new BusinessChainImpl();
        chain.addFilter(new DefaultProcessor());
        chain.addFilter(new BusinessProcessor() {
            @Override
            public void doFilter(Map<String, Object> context, BusinessChain chain) {
                context.put("first", "1");
                chain.doFilter(context);
            }
        });
        chain.addFilter(new BusinessProcessor() {
            @Override
            public void doFilter(Map<String, Object> context, BusinessChain chain) {
                context.put("second", "2");
//                chain.doFilter();
            }
        });
        HashMap<String, Object> map = new HashMap<>();
        chain.doFilter(map);
        System.out.println(map);
    }


}
