package cn.peng.studygodpath.groovy;

/**
 * Created by remote on 2017/11/13.
 */
public class TestClass extends AbstractClass{
    private String data = "data";

    public String getData(){
        text = data;
        return text;
    }

    @Override
    public void exect() {
        System.out.println("exec....");
    }
}
