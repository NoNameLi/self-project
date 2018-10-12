package cn.peng.studygodpath.spring4.autowire.test.autowiredepends;

import cn.peng.studygodpath.spring4.autowire.test.SpringConfigurable;
import cn.peng.studygodpath.spring4.autowire.test.autowiredepends.AutoWire;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by remote on 2017/9/28.
 */
public class TestDemo {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurable.class);

        AutoWire auto = context.getBean(AutoWire.class);
        System.out.println(auto.test1ServiceImpl.getName());
    }
}
