package org.example;

import com.alibaba.jvm.sandbox.api.event.Event;
import com.alibaba.jvm.sandbox.api.event.ReturnEvent;
import com.alibaba.jvm.sandbox.api.filter.Filter;
import com.alibaba.jvm.sandbox.api.listener.EventListener;

public class SpringFinishListener implements EventListener {

    private volatile boolean finish = false;

    public Filter getFilter() {
        return new Filter() {
            @Override
            public boolean doClassFilter(int access, String javaClassName, String superClassTypeJavaClassName, String[] interfaceTypeJavaClassNameArray, String[] annotationTypeJavaClassNameArray) {
                return javaClassName.equals("com.tangyh.lamp.boot.ApplicationFinishListener");
            }

            @Override
            public boolean doMethodFilter(int access, String javaMethodName, String[] parameterTypeJavaClassNameArray, String[] throwsTypeJavaClassNameArray, String[] annotationTypeJavaClassNameArray) {
                return javaMethodName.equalsIgnoreCase("onApplicationEvent");
            }
        };
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ReturnEvent) {
            finish = true;
        }
    }

    public void block() {
        while (!finish) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("run error" + e.getMessage());
                break;
            }
        }
    }


}
