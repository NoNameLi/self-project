package cn.peng.studygodpath.design.behavior.strategy.generic;

/**
 *
 */
public interface GenericExtendStrategy<T, P, E> extends GenericStrategy<T> {

    T getType();

    E handle(P param);
}
