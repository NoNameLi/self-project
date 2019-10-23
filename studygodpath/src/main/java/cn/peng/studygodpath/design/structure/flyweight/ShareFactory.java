package cn.peng.studygodpath.design.structure.flyweight;

import java.util.HashMap;

public class ShareFactory {

    private static HashMap<String, Circular> curcularMap = new HashMap<>();


    public static Circular createCircular(String color) {
        Circular circular = null;
        circular = curcularMap.get(color);
        if (null == circular) {
            circular = Circular.builder().color(color).build();
        }
        return circular;
    }


}
