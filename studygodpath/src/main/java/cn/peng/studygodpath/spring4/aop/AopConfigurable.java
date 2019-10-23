package cn.peng.studygodpath.spring4.aop;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configurable
@ComponentScan("cn.peng.studygodpath.spring4.aop")
@EnableAspectJAutoProxy // 没有这个注解 切面没有效果 也不能 使用@Autowired 完成自动注入
public class AopConfigurable {


//    @Bean
//    public BusinessService businessService() {
//        return new BusinessService();
//    }
//
//    @Bean
//    public ParamCheck paramCheck() {
//        return new ParamCheck();
//    }
//
//    @Bean
//    public ServiceAop serviceAop() {
////        return Aspects.aspectOf(ServiceAop.class);
//        return new ServiceAop();
//    }

}
