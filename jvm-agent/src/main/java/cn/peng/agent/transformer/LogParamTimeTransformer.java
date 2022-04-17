package cn.peng.agent.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;

/**
 * 记录耗时、方法入参
 */
public class LogParamTimeTransformer extends AbstractClassFileTransformer {


    public LogParamTimeTransformer(String nameRegular) {
        super(nameRegular);
    }

    @Override
    public byte[] doTransform(ClassLoader loader, String className, byte[] classfileBuffer) {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            for (CtMethod method : ctClass.getDeclaredMethods()) {
                method.addLocalVariable("agent_start", CtClass.longType);
                method.insertBefore("logger.info(\"" + method.getMethodInfo().getName() + " params:{}\", java.util.Arrays.toString($args));");
                method.insertBefore("agent_start = System.currentTimeMillis();");
                method.insertAfter("logger.info(\"" + method.getMethodInfo().getName() + " cost:\" + (System.currentTimeMillis() - agent_start));");
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 如果返回null则字节码不会被修改
        }
    }

}
