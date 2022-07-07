package cn.peng.chat.common.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ModelCmdBeanProcess implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getInterfaces()).filter(face -> face.isAnnotationPresent(Model.class))
                .forEach(face -> {
                    short model = face.getAnnotation(Model.class).model();
                    Arrays.stream(face.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(Cmd.class))
                            .forEach(method -> ModelCmdInvokeHolder.put(ModelCmdInvoke.of(model, method.getAnnotation(Cmd.class).cmd()
                                    , bean, method)));
                });
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
