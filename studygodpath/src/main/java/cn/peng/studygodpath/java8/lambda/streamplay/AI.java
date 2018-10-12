package cn.peng.studygodpath.java8.lambda.streamplay;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * Created by remote on 2018/3/21.
 */
public interface AI<T,ID>{
}

class BI implements AI<String,Integer>{


    public static void main(String[] args) {
        BI bi = new BI();
        ParameterizedType parameType = (ParameterizedType) BI.class.getGenericInterfaces()[0];
        System.out.println(BI.class.getGenericSuperclass());
        System.out.println(Arrays.toString(BI.class.getGenericInterfaces()));
        System.out.println(Arrays.toString(parameType.getActualTypeArguments()));

    }
}