package cn.peng.studygodpath.design.structure.decorator;

public abstract class Coffee {

    protected String name;

    public String getCoffeeInfo() {
        return name;
    }

    public abstract int cost();
}
