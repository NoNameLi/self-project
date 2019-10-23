package cn.peng.studygodpath.spring4.aop;

import cn.peng.studygodpath.spring4.aop.service.BusinessService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfigurable.class);
        BusinessService service = context.getBean(cn.peng.studygodpath.spring4.aop.service.BusinessService.class);
        System.out.println(service.business(null));
//        System.out.println(service.business("test"));

    }
}
