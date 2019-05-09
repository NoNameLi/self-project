package cn.peng.studygodpath.design.structure.decorator;

import org.testng.annotations.Test;

import java.io.*;

/**
 * 先分析java io 的实现 模拟
 */
public class DecoratorPlay {

    public void ioAnalysis() throws IOException {
        InputStream inputStream = new FileInputStream("");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.read(null, 10, 10);
    }

    @Test
    public void demo() {

        LanShanCoffee lanShanCoffee = new LanShanCoffee("蓝山");
        QueChaoCoffee queChaoCoffee = new QueChaoCoffee("雀巢");

        CoffeeMilkDecorator milkCoffee = new CoffeeMilkDecorator(lanShanCoffee);
        CoffeeSugarDecorator sugarCoffee = new CoffeeSugarDecorator(queChaoCoffee);
        System.out.println(milkCoffee.getCoffeeInfo() + ":" + milkCoffee.cost());
        System.out.println(sugarCoffee.getCoffeeInfo() + ":" + sugarCoffee.cost());
    }

}