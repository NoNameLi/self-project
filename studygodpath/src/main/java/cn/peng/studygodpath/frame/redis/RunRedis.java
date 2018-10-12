package cn.peng.studygodpath.frame.redis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by remote on 2017/11/13.
 */
public class RunRedis {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RedisRunConfigurable.class);


    }

}
