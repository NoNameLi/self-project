package cn.peng.studygodpath.design.structure.decorator;

public class LanShanCoffee extends Coffee {

    public LanShanCoffee(String name) {
        this.name = name;
    }

    @Override
    public int cost() {
        return 23;
    }
}
