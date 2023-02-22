package cn.peng.studygodpath.design.behavior.chain;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface Filter<T> {
    void doFilter(T filterRequestDTO, FilterChain<T> filterChain);
}


interface FilterChain<T> {
    void doFilter(T filterRequestDTO);

    void reset();
}

@Data
abstract class AbstractOrderFilter<T> implements Filter<T>, Comparable<AbstractOrderFilter<T>> {
    protected Integer order;

    @Override
    public int compareTo(AbstractOrderFilter o) {
        return getOrder().compareTo(o.getOrder());
    }

    public boolean accept(T filterRequestDTO) {
        return true;
    }

    @Override
    public void doFilter(T filterRequestDTO, FilterChain<T> filterChain) {
    }
}


@Slf4j
class ItemPermissionFilter<T> extends AbstractOrderFilter<T> {
    // 当前filter对应的业务逻辑manager（自行根据业务场景定义）
//    ItemCheckManager itemCheckManager;

    //构造器私有
    private ItemPermissionFilter(Integer order) {
        super.order = order;
//        this.itemCheckManager = itemCheckManager;
    }

    @Override
    public void doFilter(T filterRequestDTO, FilterChain<T> filterChain) {
        if (accept(filterRequestDTO)) {
//            itemCheckManager.checkItemPermission(filterRequestDTO, elementCheckResults);
        }
        //继续走责任链的下一个filter
        filterChain.doFilter(filterRequestDTO);
    }

    @Override
    public boolean accept(T filterRequestDTO) {
        //自行根据业务场景定义处理何种请求
        return true;
    }

    //对外暴露的create方法
    public static <T> ItemPermissionFilter<T> create(Integer order) {
        return new ItemPermissionFilter<T>(order);
    }
}

class CouponFilterChain<T> implements FilterChain<T> {
    /**
     * 责任链中的所有的处理组件 非变量
     */
    private final List<? extends AbstractOrderFilter<T>> filters;
    /**
     * 当前执行到的位置 这是个共享变量
     */
    private static final ThreadLocal<Integer> posLocal = ThreadLocal.withInitial(() -> 0);
    /**
     * 责任链的校验结果--即需要给用户反馈的校验结果，共享变量，threadLocal，会作为全局参数
     */
    public static final ThreadLocal<List<Object>> checkResult = new ThreadLocal<>();
    /**
     * 包含filter数量 非变量
     */
    private final int size;

    @Override
    public void doFilter(T filterRequestDTO) {
        //共享变量记住当前filterChain执行的filter的index，直至结束
        Integer pos = posLocal.get();
        if (pos < size) {
            pos++;
            posLocal.set(pos);
            Filter<T> filter = this.filters.get(pos - 1);
            filter.doFilter(filterRequestDTO, this);
        }
    }

    //供外部业务代码调用的主要方法
    public List<Object> process(T filterRequestDTO) {
        this.doFilter(filterRequestDTO);
        //将共享变量里面的结果取出来，返回给用户
//        return BaseResult.makeSuccess(checkResult.get());
        return Collections.emptyList();
    }

    //注意避免ThreadLocal内存泄漏，要remove
    @Override
    public void reset() {
        posLocal.remove();
        posLocal.set(0);
        checkResult.remove();
    }

    public CouponFilterChain(List<? extends AbstractOrderFilter<T>> filters) {
        filters.sort(AbstractOrderFilter::compareTo);
        this.filters = filters;
        this.size = filters.size();
    }
}

@Slf4j
@Component
class FilterChainManager {

//    @Resource
//    StoreManager storeManager;
//
//    @Resource
//    ItemManager itemManager;

    private CouponFilterChain<String> couponFilterChain;

    //初始化责任链
    @PostConstruct
    private void init() {
        // 总链
        List<AbstractOrderFilter<String>> filters = new ArrayList<>();
        filters.add(ItemPermissionFilter.create(100));
        // 商品校验filter
//        filters.add(ItemFilter.create(100, ItemManager));
        //门店校验filter
//        filters.add(StoreFilter.create(200, StoreManager));
        this.couponFilterChain = new CouponFilterChain<>(filters);
    }

    //供外部调用的方法
    public List<Object> process(String filterRequestDTO) {
        try {
            //责任链模式，校验每一个参数的合法性并输出错误原因
            return couponFilterChain.process(filterRequestDTO);
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            //这里非常重要 必须重置
            if (couponFilterChain != null) {
                couponFilterChain.reset();
            }
        }
    }
}


/**
 * 合成的过滤器，改过滤器内部由多个过滤器组合而成
 */
class CompositeFilter<T> extends AbstractOrderFilter<T> {
    /**
     * 所有的责任事件
     */
    private List<? extends AbstractOrderFilter<T>> filters = null;

    public CompositeFilter(Integer order, List<? extends AbstractOrderFilter<T>> filters) {
        super.order = order;
        this.filters = filters;
    }

    @Override
    public void doFilter(T filterRequestDTO, FilterChain<T> filterChain) {
        (new InnerFilterChain<T>(filterChain, this.filters)).doFilter(filterRequestDTO);
    }

    /**
     * 内部链处理逻辑，优先将合成过滤器的内部过滤器进行处理，然后再传给下一个过滤器
     */
    private static class InnerFilterChain<T> implements FilterChain<T> {
        private final FilterChain<T> originalChain;
        private final List<? extends AbstractOrderFilter<T>> additionalFilters;
        private int currentPosition = 0;

        public InnerFilterChain(FilterChain<T> chain, List<? extends AbstractOrderFilter<T>> additionalFilters) {
            this.originalChain = chain;
            this.additionalFilters = additionalFilters;
        }

        @Override
        public void doFilter(T filterRequestDTO) {
            if (this.currentPosition >= this.additionalFilters.size()) {
                //如果已经执行完了内部过滤器，则跳到外部继续执行外部下一个节点的过滤器
                this.originalChain.doFilter(filterRequestDTO);
            } else {
                //继续执行内部过滤器
                this.currentPosition++;
                AbstractOrderFilter<T> currentFilter = this.additionalFilters.get(this.currentPosition - 1);
                currentFilter.doFilter(filterRequestDTO, this);
            }
        }

        @Override
        public void reset() {
        }
    }

    public static <T> CompositeFilter<T> create(Integer order, List<? extends AbstractOrderFilter<T>> filters) {
        filters.sort(AbstractOrderFilter::compareTo);
        return new CompositeFilter<T>(order, filters);
    }
}


public class DemoChain {

    public static void main(String[] args) {

    }
}
