package cn.peng.agent.transformer;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 恢复修改的类
 */
public class RestoreTransformer extends AbstractClassFileTransformer {
    public RestoreTransformer(String nameRegular) {
        super(nameRegular);
    }

    @Override
    public byte[] doTransform(ClassLoader loader, String className, byte[] classfileBuffer) {
        String classFileName = className.replace(".", "/") + ".class";
        try (InputStream classStream = ClassLoader.getSystemResourceAsStream(classFileName)) {
            return IOUtils.toByteArray(classStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
