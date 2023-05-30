package org.example;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.ModuleLifecycle;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;

/**
 * @Author: Administrator
 * @CreateTime:2023-05-17 16:53
 * QDescription:
 */
@MetaInfServices(Module.class)
@Information(id = "Spring Start Monitor")
public class SpringTimeModule implements Module, ModuleLifecycle {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    private static final String LOG_PREFIX = "[SpringStartMonitor]";

    @Override
    public void onLoad() throws Throwable {// 模块加载，
        System.out.println(LOG_PREFIX + "onLoad");
    }

    @Override
    public void onUnload() throws Throwable {// 模块卸载
        System.out.println(LOG_PREFIX + "onUnload");
    }

    @Override
    public void onActive() throws Throwable {// 模块激活，模块所增加的类会被激活
        System.out.println(LOG_PREFIX + "onActive");
    }

    @Override
    public void onFrozen() throws Throwable {// 模块冻结，模块的事件监听者不再响应事件
        System.out.println(LOG_PREFIX + "onFrozen");
    }

    @Override
    public void loadCompleted() {
        new SpringBeanStartMonitor(moduleEventWatcher).start();
        System.out.println(LOG_PREFIX + "loadCompleted");
    }
}
