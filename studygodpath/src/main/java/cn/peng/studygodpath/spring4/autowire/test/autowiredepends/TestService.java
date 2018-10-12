package cn.peng.studygodpath.spring4.autowire.test.autowiredepends;

import org.springframework.stereotype.Service;

/**
 * Created by remote on 2017/9/25.
 */
public interface TestService {

    default String getName() {
        Class clazz = getClass();
        if(clazz.isAnnotationPresent(Service.class)){
            Service service = (Service)clazz.getAnnotation(Service.class);
            return service.value();
        }else{
            return null;
        }
    }
}
