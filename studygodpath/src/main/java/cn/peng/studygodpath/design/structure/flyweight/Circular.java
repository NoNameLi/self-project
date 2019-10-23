package cn.peng.studygodpath.design.structure.flyweight;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Circular implements Share {
    private String color;

    private int radius;

    private int x;
    private int y;

    @Override
    public void draw() {
        System.out.println(this.toString());
    }
}
