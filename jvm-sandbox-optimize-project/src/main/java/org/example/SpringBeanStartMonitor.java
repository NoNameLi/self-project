package org.example;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.jvm.sandbox.api.event.Event;
import com.alibaba.jvm.sandbox.api.filter.Filter;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;

import java.util.Map;

public class SpringBeanStartMonitor extends Thread {

    private final ModuleEventWatcher watcher;

    private final SpringBeanInitListener listener;

    private final SpringFinishListener finishListener;

    public SpringBeanStartMonitor(ModuleEventWatcher watcher) {
        this.watcher = watcher;
        this.listener = new SpringBeanInitListener();
        this.finishListener = new SpringFinishListener();
    }

    @Override
    public void run() {
        watcher.watch(this.listener.getFilter(), this.listener, Event.Type.BEFORE, Event.Type.RETURN);
        watcher.watch(this.finishListener.getFilter(), this.finishListener, Event.Type.RETURN);

        finishListener.block();

        this.exportCsvData();
        System.out.println("---------------------------");
        System.out.println("        导出完成            ");
        System.out.println("---------------------------");
    }

    public void exportCsvData() {
        Map<String, TimeBean> springBeanMap = listener.getMap();
        String filePath = System.getProperty("user.dir") + "/springBean.csv";
        System.out.println("导出文件路径：" + filePath);
        CsvWriter writer = CsvUtil.getWriter(filePath, CharsetUtil.CHARSET_UTF_8);
        writer.writeBeans(springBeanMap.values());
        writer.close();
    }
}
