package cn.peng.studygodpath.java8.observer;

import java.util.Observable;
import java.util.Observer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JavaObserver implements Observer {

    private Integer flag;

    @Override
    public void update(Observable o, Object arg) {
        if (flag == null || arg == flag) {
            Subject sub = (Subject) o;
            System.out.println(sub.getData());
        }
    }

}
