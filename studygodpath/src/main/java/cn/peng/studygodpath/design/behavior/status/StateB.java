package cn.peng.studygodpath.design.behavior.status;

/**
 * Created by remote on 2018/3/2.
 */
public class StateB extends State{

    @Override
    public void handle(Context context) {
        context.setState(new StateA());
    }
}
