package cn.peng.studygodpath.java8.lambda.streamplay;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by remote on 2018/3/21.
 *
 * 如果是继承基类而来的泛型，就用 getGenericSuperclass() , 转型为 ParameterizedType 来获得实际类型
 * 如果是实现接口而来的泛型，就用 getGenericInterfaces() , 针对其中的元素转型为 ParameterizedType 来获得实际类型
 */



class B extends A<String,Integer>{

}


class A<T,ID> {

    public Class getTClass(int index) {

        Type genType = getClass().getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index out of bounds");
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    public static void main(String[] args) {
        B aa = new B();
        ParameterizedType parameType = (ParameterizedType) aa.getClass().getGenericSuperclass();
        System.out.println(aa.getClass().getGenericSuperclass());
        System.out.println(parameType.getTypeName());
        System.out.println(parameType.getOwnerType());
        System.out.println(parameType.getRawType());
        System.out.println(Arrays.toString(parameType.getActualTypeArguments()));
    }



}
