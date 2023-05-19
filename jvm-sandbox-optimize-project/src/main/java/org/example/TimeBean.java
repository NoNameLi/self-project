package org.example;

/**
 * @Author: Administrator
 * @CreateTime:2023-05-17 17:34
 * QDescription:
 */
public class TimeBean {
    private String name;
    private String className;
    private String classLoadName;

    public static TimeBean of(String name, String className, String classLoadName) {
        TimeBean bean = new TimeBean();
        bean.name = name;
        bean.className = className;
        bean.classLoadName = classLoadName;
        return bean;
    }

    private long start;
    private long finish;

    private long cost;

    public void start() {
        this.start = System.currentTimeMillis();
    }

    public void finish() {
        this.finish = System.currentTimeMillis();
        this.cost = this.finish - this.start;
    }


    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getClassLoadName() {
        return classLoadName;
    }

    public long getStart() {
        return start;
    }

    public long getFinish() {
        return finish;
    }

    public long getCost() {
        return cost;
    }
}
