package cn.peng.studygodpath.frame.redis.test;

import cn.peng.studygodpath.frame.redis.install.RedisClusterUtil;
import cn.peng.studygodpath.frame.redis.install.RedisUtil;
import org.joda.time.DateTime;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import redis.clients.jedis.Jedis;

/**
 * Created by remote on 2018/5/31.
 */
public class ClientThread extends Thread {
    int i = 0;

    public ClientThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {

//        RedisClusterUtil.setString("foo", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//        String foo = RedisClusterUtil.getString("foo");
//        System.out.println("【输出>>>>】foo:" + foo + " 第：" + i + "个线程" + "当前时间：" + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        RedisUtil.setString("foo", DateTime.now().toString("yyyy-MM-dd HH:mm:ss:sss"));
        String foo = RedisUtil.getString("foo");
        System.out.println("【输出>>>>】foo:" + foo + " 第：" + i + "个线程" + "当前时间：" + DateTime.now().toString("yyyy-MM-dd HH:mm:ss:sss"));

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            ClientThread t = new ClientThread(i);
            t.start();
        }
//        Jedis jedis = new Jedis("192.168.184.129",6379);
//        System.out.println(jedis.get("test"));
//        System.out.println(RedisUtil.getString("test"));
//        Jedis redis = new Jedis("192.168.184.129", 7001);
//        Jedis redis = new Jedis("192.168.184.129", 6379);
//        System.out.println(redis.get("test"));
//        redis.close();
    }


}
