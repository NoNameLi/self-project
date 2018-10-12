package cn.peng.studygodpath.design.behavior.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User implements Observer {

    private String name;

    @Override
    public void update(Object message) {
        System.out.println(this.name + ":" + message);
    }

}
