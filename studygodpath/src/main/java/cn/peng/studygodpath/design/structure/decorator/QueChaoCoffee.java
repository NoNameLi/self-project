package cn.peng.studygodpath.design.structure.decorator;

public class QueChaoCoffee extends Coffee {
    public QueChaoCoffee(String name) {
        this.name = name;
    }

    @Override
    public int cost() {
        return 18;
    }
}
