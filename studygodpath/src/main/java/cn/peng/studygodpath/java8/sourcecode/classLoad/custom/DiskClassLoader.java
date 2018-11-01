package cn.peng.studygodpath.java8.sourcecode.classLoad.custom;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DiskClassLoader extends ClassLoader {

    private String parentPath;

    public DiskClassLoader(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * @param name 类的全限定名
     * @return
     * @throws ClassNotFoundException
     * 注意： 读取文件时：使用class文件在文件系统的路径
     *  defineClass 第一个参数 传入的是class 的全限定名，此处出错会出 NotClassDefFoundException
     *
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(parentPath, getClassName(name));
        Class<?> defineClass = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] byteArray = IOUtils.toByteArray(fis);
            defineClass = defineClass(name, byteArray, 0, byteArray.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass;
    }

    private String getClassName(String name) {

        name = name.replaceAll("\\.", File.separator.equals("\\") ? "\\\\" : File.separator);
        if (!name.endsWith(".class")) {
            name = name + ".class";
        }
        return name;
    }
}
