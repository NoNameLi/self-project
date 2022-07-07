package cn.peng.chat.common.annotation;

import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
public class ModelCmdInvoke {

    private short model;
    private short cmd;
    private Object object;
    private Method method;

    public static ModelCmdInvoke of(short model, short cmd, Object object, Method method) {
        ModelCmdInvoke invoke = new ModelCmdInvoke();
        invoke.model = model;
        invoke.cmd = cmd;
        invoke.object = object;
        invoke.method = method;
        return invoke;
    }

    public Object invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object, args);
    }

}
