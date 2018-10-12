package cn.peng.studygodpath.java8.copy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ObjStream implements Cloneable, Serializable {
    private static final long serialVersionUID = -832219797355648540L;

    String str = "init value";

    Obj obj;

    @Override
    public ObjStream clone() throws CloneNotSupportedException {
        // 使用流化实现深度克隆 此方式可以不用实现 Cloneable 接口
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (ObjStream) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

}
