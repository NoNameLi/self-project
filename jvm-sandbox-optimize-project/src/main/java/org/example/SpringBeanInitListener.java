package org.example;


import com.alibaba.jvm.sandbox.api.event.BeforeEvent;
import com.alibaba.jvm.sandbox.api.event.Event;
import com.alibaba.jvm.sandbox.api.event.InvokeEvent;
import com.alibaba.jvm.sandbox.api.event.ReturnEvent;
import com.alibaba.jvm.sandbox.api.filter.Filter;
import com.alibaba.jvm.sandbox.api.listener.EventListener;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;

import java.util.HashMap;
import java.util.Optional;

/**
 * @Author: Administrator
 * @CreateTime:2023-05-17 17:28
 * QDescription:
 */
public class SpringBeanInitListener implements EventListener {
    private HashMap<String, TimeBean> map = new HashMap<>();


    public Filter getFilter() {
        return new Filter() {
            @Override
            public boolean doClassFilter(int access, String javaClassName, String superClassTypeJavaClassName, String[] interfaceTypeJavaClassNameArray, String[] annotationTypeJavaClassNameArray) {
                return javaClassName.equals("org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory");
            }

            @Override
            public boolean doMethodFilter(int access, String javaMethodName, String[] parameterTypeJavaClassNameArray, String[] throwsTypeJavaClassNameArray, String[] annotationTypeJavaClassNameArray) {
                return javaMethodName.equalsIgnoreCase("initializeBean");
            }
        };
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof BeforeEvent) {
            BeforeEvent beforeEvent = (BeforeEvent) event;
            String key = getKey(beforeEvent);
            if (map.containsKey(key)) {
                return;
            }
            TimeBean timeBean = TimeBean.of(beforeEvent.argumentArray[0].toString(),
                    beforeEvent.argumentArray[1].getClass().toString()
                    , beforeEvent.argumentArray[1].getClass().getClassLoader().toString());
            timeBean.start();
            map.put(key, timeBean);
        } else if (event instanceof ReturnEvent) {
            ReturnEvent returnEvent = (ReturnEvent) event;
            Optional.of(map.get(getKey(returnEvent))).ifPresent(TimeBean::finish);
        }
    }

    private String getKey(InvokeEvent event) {
        return event.processId + "-" + event.invokeId;
    }

    public HashMap<String, TimeBean> getMap() {
        return map;
    }
}
