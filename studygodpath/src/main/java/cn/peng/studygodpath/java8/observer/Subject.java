package cn.peng.studygodpath.java8.observer;

import java.util.Observable;

import lombok.Getter;

@Getter
public class Subject extends Observable {

    public static final Integer ODD = 1;// 奇数

    public static final Integer EVEN = 2;// 偶数

    public int data;

    public void setData(int data) {
        if (this.data != data) {
            this.data = data;
            setChanged();
        }
        Integer flag = EVEN;
        if ((this.data & 0x0001) == 1)
            flag = ODD;
        notifyObservers(flag);
    }

}
