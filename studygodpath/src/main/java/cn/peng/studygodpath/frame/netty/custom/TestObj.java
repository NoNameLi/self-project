package cn.peng.studygodpath.frame.netty.custom;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Field;

@Data
@ToString
public class TestObj {

    private int a;
    private Integer b;

    public TestObj() {
    }

    public TestObj(int a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        TestObj testObj = new TestObj(10, 20);
        test(testObj);
        Object[] arr = new TestObj[10];

    }

    public static void test(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            Object o = field.get(obj);
            if (field.getType() == int.class) {
                System.out.println("filed " + field.getName() + " is int" + o.getClass());
            }
            if (field.getType() == Integer.class) {
                System.out.println("filed " + field.getName() + " is Integer" + o.getClass());
            }
        }


    }
}
