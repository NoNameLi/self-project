package cn.peng.studygodpath.java8.copy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class BObj implements Cloneable {

    String name;

    int count;

    Obj obj;

    ObjClone cloneObj;

    ObjStream streamObj;

    @Override
    public BObj clone() throws CloneNotSupportedException {
        BObj o = null;
        o = (BObj) super.clone();
        // 参考 @AutoClone
        o.obj = obj instanceof Cloneable ? obj.clone() : obj;
        o.cloneObj = cloneObj instanceof Cloneable ? cloneObj.clone() : cloneObj;
        // 此方式可以不用实现 Cloneable 接口
        o.streamObj = streamObj instanceof Cloneable ? streamObj.clone() : streamObj;
        return o;
    }

}
