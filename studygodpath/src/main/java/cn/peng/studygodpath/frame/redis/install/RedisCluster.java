package cn.peng.studygodpath.frame.redis.install;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by remote on 2018/5/31.
 */
public class RedisCluster {


    public static void main(String[] args) throws IOException {
        String ip = "192.168.184.129";
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(ip, 7001));
        nodes.add(new HostAndPort(ip, 7002));
        nodes.add(new HostAndPort(ip, 7003));
        nodes.add(new HostAndPort(ip, 7004));
        nodes.add(new HostAndPort(ip, 7005));
        nodes.add(new HostAndPort(ip, 7006));


        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMaxTotal(100);
        cfg.setMaxIdle(20);
        cfg.setMaxWaitMillis(-1);
        cfg.setTestOnBorrow(true);
        JedisCluster jc = new JedisCluster(nodes,6000,100,cfg);

        JedisCluster cluster = new JedisCluster(nodes);

        System.out.println(cluster.get("name"));
        System.out.println(cluster.set("name","lcp"));
        System.out.println(cluster.get("name"));
        cluster.close();
    }
}
