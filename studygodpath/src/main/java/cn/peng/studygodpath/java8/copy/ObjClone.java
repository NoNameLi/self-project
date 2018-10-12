package cn.peng.studygodpath.java8.copy;

import lombok.ToString;

@ToString
public class ObjClone implements Cloneable {
    String str = "init value";

    @Override
    public ObjClone clone() throws CloneNotSupportedException {
        // 可参考 @AutoClone
        ObjClone clone = (ObjClone) super.clone();
        return clone;
    }

}
