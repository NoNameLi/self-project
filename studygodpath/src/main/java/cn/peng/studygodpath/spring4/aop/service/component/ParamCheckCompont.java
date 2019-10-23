package cn.peng.studygodpath.spring4.aop.service.component;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ParamCheckCompont implements ParamCheck {

    @Override
    public boolean check(Object[] param) {
        System.out.println("开始校验");
        if (null != param) {
            AtomicBoolean flag = new AtomicBoolean(true);
            Arrays.stream(param).forEach(item -> {
                if (null == item) {
                    flag.set(false);
                }
            });
            return flag.get();
        } else {
            return true;
        }
    }
}
