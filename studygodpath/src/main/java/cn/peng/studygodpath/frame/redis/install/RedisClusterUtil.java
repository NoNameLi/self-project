package cn.peng.studygodpath.frame.redis.install;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by remote on 2018/5/31.
 */
public class RedisClusterUtil {

    protected static ReentrantLock lockClustPool = new ReentrantLock();

    protected static Logger logger = LoggerFactory.getLogger(RedisClusterUtil.class);

    //Redis服务器IP(虚拟接ip)
    private static String[] ADDR_ARRAY = {"192.168.184.129", "192.168.184.129", "192.168.184.129", "192.168.184.129", "192.168.184.129", "192.168.184.129"};
    //Redis的端口号
    private static int[] PORT_ARRAY = {7001, 7002, 7003, 7004, 7005, 7006,};

    //访问密码
    private static String AUTH = "";
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 100;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 20;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 3000;

    //超时时间
    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    //private static JedisPool jedisPool = null;
    private static JedisCluster cluster = null;

    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60 * 60;            //一小时
    public final static int EXRP_DAY = 60 * 60 * 24;        //一天
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30; //一个月

    /**
     * 初始化Redis连接池
     */
    private static void initialClusterPool() {
        try {
            HashSet<HostAndPort> nodes = new HashSet<>();
            for (int i = 0; i < ADDR_ARRAY.length; i++) {
                nodes.add(new HostAndPort(ADDR_ARRAY[i], PORT_ARRAY[i]));
            }

            JedisPoolConfig cfg = new JedisPoolConfig();
            cfg.setMaxTotal(MAX_ACTIVE);
            cfg.setMaxIdle(MAX_IDLE);
            cfg.setMaxWaitMillis(MAX_WAIT);
            cfg.setTestOnBorrow(TEST_ON_BORROW);

            cluster = new JedisCluster(nodes, 6000, 100, cfg);
        } catch (Exception e) {
            logger.error("create Jedis Pool Cluster error : " + e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static JedisCluster getJedisCluster() {
        lockClustPool.lock();
        try {
            if (cluster == null) {
                initialClusterPool();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockClustPool.unlock();
        }
        return cluster;
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized static void setString(String key, String value) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            getJedisCluster().set(key, value);
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 设置 过期时间
     *
     * @param key
     * @param seconds 以秒为单位
     * @param value
     */
    public synchronized static void setString(String key, int seconds, String value) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            getJedisCluster().setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("Set keyex error : " + e);
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized static String getString(String key) {
        if (getJedisCluster() == null || !getJedisCluster().exists(key)) {
            return null;
        }
        return getJedisCluster().get(key);
    }
}
