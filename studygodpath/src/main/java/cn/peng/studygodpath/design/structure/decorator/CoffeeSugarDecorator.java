package cn.peng.studygodpath.design.structure.decorator;

public class CoffeeSugarDecorator extends CoffeeDecorator {

    public CoffeeSugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getCoffeeInfo() {
        return this.coffee.getCoffeeInfo() + ":加糖";
    }

    @Override
    public int cost() {
        return this.coffee.cost() + 3;
    }
}
