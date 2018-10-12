package cn.peng.studygodpath.spring4.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * Created by remote on 2018/6/12.
 */
@Configuration
//@ImportResource("classpath:/spring/redis.xml")
@PropertySource("classpath:/spring/redis.properties")
public class RedisConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        //可用连接实例的最大数目
        config.setMaxTotal(20);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
        config.setMaxIdle(8);
        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(3000);
        //在获取一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(false);
        //在归还一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnReturn(false);
        //Idle时进行连接扫描
        config.setTestWhileIdle(true);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setTimeBetweenEvictionRunsMillis(30000);
        //表示idle object evitor每次扫描的最多的对象数
        config.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(60000);
        return config;
    }

    @Bean
    public JedisCluster jediscluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(env.getProperty("redis.host1"), Integer.valueOf(env.getProperty("redis.post1"))));
        nodes.add(new HostAndPort(env.getProperty("redis.host2"), Integer.valueOf(env.getProperty("redis.post2"))));
        nodes.add(new HostAndPort(env.getProperty("redis.host3"), Integer.valueOf(env.getProperty("redis.post3"))));
        nodes.add(new HostAndPort(env.getProperty("redis.host4"), Integer.valueOf(env.getProperty("redis.post4"))));
        nodes.add(new HostAndPort(env.getProperty("redis.host5"), Integer.valueOf(env.getProperty("redis.post5"))));
        nodes.add(new HostAndPort(env.getProperty("redis.host6"), Integer.valueOf(env.getProperty("redis.post6"))));
        JedisCluster cluster = new JedisCluster(nodes,6000,100,jedisPoolConfig());
        return cluster;
    }
}
