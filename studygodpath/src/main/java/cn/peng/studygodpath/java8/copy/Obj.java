package cn.peng.studygodpath.java8.copy;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class Obj implements Serializable {
    private static final long serialVersionUID = 1031898123261025405L;
    String str = "init value";

    @Override
    public Obj clone() throws CloneNotSupportedException {
        return (Obj) super.clone();
    }
}
