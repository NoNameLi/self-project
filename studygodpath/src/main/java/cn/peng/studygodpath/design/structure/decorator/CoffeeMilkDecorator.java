package cn.peng.studygodpath.design.structure.decorator;

public class CoffeeMilkDecorator extends CoffeeDecorator {

    public CoffeeMilkDecorator(Coffee name) {
        this.coffee = name;
    }

    @Override
    public String getCoffeeInfo() {
        return this.coffee.getCoffeeInfo() + ":奶油";
    }

    @Override
    public int cost() {
        return this.coffee.cost() + 5;
    }
}
