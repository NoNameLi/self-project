package cn.peng.studygodpath.java8.lambda.streamplay;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by remote on 2018/3/17.
 */
public interface MapDataProducerInterface<T> {

    T production(int t);

    default Class getTClass(){
        Type[] interfacesType = this.getClass().getGenericInterfaces();
        Optional<Type> typeOptional = Arrays.stream(interfacesType).filter(type -> (type instanceof ParameterizedType)).findFirst();
        return (Class)((ParameterizedType) typeOptional.get()).getActualTypeArguments()[0];
    }

}
