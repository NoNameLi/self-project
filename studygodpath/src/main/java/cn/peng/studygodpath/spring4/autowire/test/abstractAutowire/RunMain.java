package cn.peng.studygodpath.spring4.autowire.test.abstractAutowire;

import cn.peng.studygodpath.spring4.autowire.test.SpringConfigurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by remote on 2017/11/13.
 */
public class RunMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurable.class);
        GoogleHtmlParser parser = context.getBean(GoogleHtmlParser.class);
        parser.parse();
    }

}
