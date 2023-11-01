package cn.peng.studygodpath.java8.reflex;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class MethodParamsNameTest {

    public String methodParams(String str, int i) {

        return "";
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<MethodParamsNameTest> aClass = MethodParamsNameTest.class;

        Method method = aClass.getDeclaredMethod("methodParams", String.class, Integer.TYPE);

        Class<?>[] parameterTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();
        Arrays.stream(parameters).forEach(item -> System.out.println(item.getType() + "：" + item.getName()));


    }


}
