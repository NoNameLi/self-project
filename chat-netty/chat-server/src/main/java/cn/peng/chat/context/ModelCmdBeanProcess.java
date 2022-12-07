package cn.peng.chat.context;

import cn.peng.chat.common.annotation.Cmd;
import cn.peng.chat.common.annotation.Model;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ModelCmdBeanProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getInterfaces()).filter(face -> face.isAnnotationPresent(Model.class))
                .forEach(face -> {
                    Model model = face.getAnnotation(Model.class);
                    Arrays.stream(face.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(Cmd.class))
                            .forEach(method -> ModelCmdInvokeHolder.put(ModelCmdInvoke.of(model, method.getAnnotation(Cmd.class), bean, method)));
                });
        return null;
    }
}
