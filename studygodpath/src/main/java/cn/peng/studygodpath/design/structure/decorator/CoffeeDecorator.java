package cn.peng.studygodpath.design.structure.decorator;

public abstract class CoffeeDecorator extends Coffee {

    protected Coffee coffee;

    public abstract String getCoffeeInfo();
}

