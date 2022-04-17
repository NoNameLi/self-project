package cn.peng.agent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public abstract class AbstractClassFileTransformer implements ClassFileTransformer {
    private String nameRegular;

    public AbstractClassFileTransformer(String nameRegular) {
        this.nameRegular = nameRegular;
    }

    public abstract byte[] doTransform(ClassLoader loader, String className, byte[] classfileBuffer);

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.matches(nameRegular)) {
            return this.doTransform(loader, className, classfileBuffer);
        } else {
            return null;// 如果返回null则字节码不会被修改
        }

    }
}
