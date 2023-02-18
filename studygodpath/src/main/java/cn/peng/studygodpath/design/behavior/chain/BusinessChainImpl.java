package cn.peng.studygodpath.design.behavior.chain;

import java.util.*;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-18 09:16
 * QDescription:
 */
public class BusinessChainImpl implements BusinessChain {

    private List<BusinessProcessor> filters;

    Iterator<BusinessProcessor> iterator = null;


    public BusinessChainImpl() {
        this.filters = new ArrayList<>();
    }

    @Override
    public void addFilter(BusinessProcessor filter) {
        this.filters.add(filter);
    }

    @Override
    public List<BusinessProcessor> listFilter() {
        return this.filters;
    }

    @Override
    public void doFilter( Map<String,Object> context) {
        if (Objects.isNull(iterator)) {
            iterator = this.filters.iterator();
        }

        if (iterator.hasNext()) {
            iterator.next().doFilter(context, this);
        }
    }

    @Override
    public void release() {

    }
}
