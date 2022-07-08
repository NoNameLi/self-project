package cn.peng.chat.context;

import cn.peng.chat.common.annotation.Cmd;
import cn.peng.chat.common.annotation.Model;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
public class ModelCmdInvoke {

    private short model;
    private short cmd;
    private boolean needLogin;
    private Object object;
    private Method method;


    public static ModelCmdInvoke of(Model model, Cmd cmd, Object object, Method method) {

        return ModelCmdInvoke.of(model.value(), cmd.value(), model.needLogin() && cmd.needLogin(), object, method);
    }

    public static ModelCmdInvoke of(short model, short cmd, boolean needLogin, Object object, Method method) {
        ModelCmdInvoke invoke = new ModelCmdInvoke();
        invoke.model = model;
        invoke.cmd = cmd;
        invoke.needLogin = needLogin;
        invoke.object = object;
        invoke.method = method;
        return invoke;
    }

    public byte[] invoke(byte[] argsData) throws InvocationTargetException, IllegalAccessException {
        // TODO 想法反序列化出参数
//        Object result = method.invoke(object, args);
        // TODO 想法序列化字节数组
        return null;
    }

}
