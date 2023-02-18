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
        ProcessorPriceChain chain = new ProcessorPriceChain();
        chain.addFilter(new DefaultProcessor());
        chain.addFilter((context, chain1) -> {
            context.put("first", "1");
            chain1.doFilter(context);
        });
        chain.addFilter((context, chain12) -> {
            context.put("second", "2");
//                chain.doFilter();
        });
        HashMap<String, Object> map = new HashMap<>();
        chain.doFilter(map);
        System.out.println(map);
    }


}
