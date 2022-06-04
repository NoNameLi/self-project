package cn.peng.studygodpath.frame.netty.custom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 自定义序列化协议 ，数据为byte数组
 * <p>
 * 1. 对象 byte boolean char short int long float double string object list map array
 * 2. 支持序列化 反序列化
 * 3. 代码入侵少
 * 4. 每个属性有默认值处理 基本类型对应的默认值，string 默认空字符串  list、map array 空集合
 */
public class CustomSerialization {
//    private ChannelBuffer byteBuf = ChannelBuffers.dynamicBuffer(); //netty3

    private Comparator fieldComparator = Comparator.comparing(Field::getName);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TestObj obj = new TestObj(1, 2);

        CustomSerialization customSerialization = new CustomSerialization();
        byte[] bytes = customSerialization.toByte(obj);
        System.out.println(Arrays.toString(bytes));
        TestObj testObj = customSerialization.fromByte(bytes, TestObj.class);
        System.out.println(testObj.toString());

    }


    /**
     * 将对象属性序列化
     */
    public byte[] toByte(Object obj) throws IllegalAccessException {
        ByteBuf buffer = Unpooled.buffer();

        if (Objects.isNull(obj)) return new byte[0];
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        Arrays.sort(fields, fieldComparator);
        for (Field f : fields) {
            f.setAccessible(true);
            Object o = f.get(obj);
            Class<?> type = f.getType();
            writeData(buffer, type, o);

            if (Collection.class.isAssignableFrom(type)) {// TODO

            }
            if (Map.class.isAssignableFrom(type)) { //TODO
            }
        }
        return ByteBufUtil.getBytes(buffer);
    }

    private void writeData(ByteBuf buffer, Class<?> type, Object o) throws IllegalAccessException {
        if (type == byte.class || type == Byte.class) {
            buffer.writeByte(null == o ? 0 : (byte) o);
        }
        if (type == boolean.class || type == Boolean.class) {
            buffer.writeBoolean(null == o ? false : (boolean) o);
        }
        if (type == char.class || type == Character.class) {
            buffer.writeChar(null == o ? 0 : (char) o);
        }
        if (type == short.class || type == Short.class) {
            buffer.writeShort(null == o ? 0 : (short) o);
        }
        if (type == int.class || type == Integer.class) {
            buffer.writeInt(null == o ? 0 : (int) o);
        }
        if (type == long.class || type == Long.class) {
            buffer.writeLong(null == o ? 0L : (long) o);
        }
        if (type == float.class || type == Float.class) {
            buffer.writeFloat(null == o ? 0f : (float) o);
        }
        if (type == double.class || type == Double.class) {
            buffer.writeDouble(null == o ? 0d : (double) o);
        }
        if (type == String.class) {
            if (null == o) {
                buffer.writeInt(0);
            } else {
                byte[] bytes = ((String) o).getBytes();
                buffer.writeInt(bytes.length);
                buffer.writeBytes(bytes);
            }
        }
        if (type.isArray()) {
            // 长度 为空 默认0
            if (null == o) {
                buffer.writeInt(0);
            } else {
                Object[] arr = (Object[]) o;
                buffer.writeInt(arr.length);
                for (Object element : arr) {
                    buffer.writeBytes(toByte(element));
                }
            }
        }
    }


    public <T> T fromByte(byte[] data, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        ByteBuf buffer = Unpooled.copiedBuffer(data);
        return fromBuffer(buffer, clazz);
    }

    /**
     * 反序列化
     */
    public <T> T fromBuffer(ByteBuf buffer, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        Arrays.sort(fields, fieldComparator);
        T t = clazz.newInstance();
        for (Field f : fields) {
            Class<?> type = f.getType();
            Object value = readType(buffer, type);
            f.setAccessible(true);
            f.set(t, value);
        }
        return t;
    }

    private Object readType(ByteBuf buffer, Class<?> type) throws InstantiationException, IllegalAccessException {
        if (type == byte.class || type == Byte.class) {
            return buffer.readByte();
        }
        if (type == boolean.class || type == Boolean.class) {
            return buffer.readBoolean();
        }
        if (type == char.class || type == Character.class) {
            return buffer.readChar();
        }
        if (type == short.class || type == Short.class) {
            return buffer.readShort();
        }
        if (type == int.class || type == Integer.class) {
            return buffer.readInt();
        }
        if (type == long.class || type == Long.class) {
            return buffer.readLong();
        }
        if (type == float.class || type == Float.class) {
            return buffer.readFloat();
        }
        if (type == double.class || type == Double.class) {
            return buffer.readDouble();
        }
        if (type == String.class) {
            int length = buffer.readInt();
            if (length == 0)
                return "";
            return String.valueOf(buffer.readBytes(length));
        }
        if (type.isArray()) {
            int i = buffer.readInt();
            if (i == 0) {
                return type.newInstance();
            } else {
                Class<?> elementType = type.getComponentType();
                Object[] arr = new Object[i];
                for (int j = 0; j < i; j++) {
                    arr[j] = fromBuffer(buffer, elementType);
                }
                return arr;
            }
        }
        return null;
    }

}
