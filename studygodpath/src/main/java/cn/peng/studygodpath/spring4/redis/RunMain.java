package cn.peng.studygodpath.spring4.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.JedisCluster;

/**
 * Created by remote on 2018/6/12.
 */
public class RunMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigurable.class);

        JedisCluster jedisCluster = context.getBean(JedisCluster.class);
        jedisCluster.set("spring redis", "ok");
        System.out.println(jedisCluster.get("spring redis"));


    }

}
