package cn.peng.studygodpath.design.structure.flyweight;

import java.util.Random;

public class TestMain {

    static String[] colors = {"red", "yellow", "greed", "blank", "pink"};

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Circular circular = ShareFactory.createCircular(randomColor(random));
            circular.setX(randomInt(random));
            circular.setY(randomInt(random));
            circular.draw();
        }


    }

    public static String randomColor(Random random) {
        return colors[random.nextInt(colors.length)];
    }

    public static int randomInt(Random random) {
        return random.nextInt();
    }


}
