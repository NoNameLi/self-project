package cn.peng.agent;

import cn.peng.agent.transformer.LogParamTimeTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 打印入参代理
 */
public class Agent {

    public static void main(String[] args) {
        System.out.println("main run");
    }

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * 若不存在 premain(String agentArgs, Instrumentation inst)，
     * 则会执行 premain(String agentArgs)
     */
    public static void premain(String nameRegular, Instrumentation instrumentation) {
        instrumentation.addTransformer(new LogParamTimeTransformer(nameRegular), true);
    }

    /**
     * 该方法在运行时执行，
     * 若不存在 agentmain(String agentArgs, Instrumentation inst)，
     * 则会执行 agentmain(String agentArgs)
     */
    public static void agentmain(String nameRegular, Instrumentation instrumentation) {
        // 解析

        instrumentation.addTransformer(new LogParamTimeTransformer(nameRegular), true);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        try {
            for (Class clazz : allLoadedClasses) {
                if (clazz.getName().matches(nameRegular)) {
                    instrumentation.retransformClasses(clazz);
                }

            }
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }
}
