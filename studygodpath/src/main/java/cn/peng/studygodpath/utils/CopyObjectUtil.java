package cn.peng.studygodpath.utils;

import java.io.*;

/**
 * 深度克隆 拷贝对象
 */
public class CopyObjectUtil {


    public static <T extends Serializable> T deepCopy(T source) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objOutputStream = new ObjectOutputStream(byteOutputStream);
            objOutputStream.writeObject(source);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
            T object = (T) objectInputStream.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
