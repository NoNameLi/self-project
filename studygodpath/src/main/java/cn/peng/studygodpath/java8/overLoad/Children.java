package cn.peng.studygodpath.java8.overLoad;

import lombok.val;

/**
 * Created by remote on 2018/1/22.
 */
public class Children extends Parent {

    private int val = 0;

    @Override
    public String getName() {
        return "children";
    }

    @Override
    public boolean equals(Object tar) {
        if (tar == this) {
            return true;
        } else {
            if (tar instanceof Children) {
                Children tarC = (Children) tar;
                return this.val == tarC.val;
            }
            return false;
        }
    }
}
