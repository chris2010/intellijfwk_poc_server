package com.clt.framework.romote.Executor.RedisExcutors;


import com.clt.base.exception.FrameworkException;
import com.clt.framework.common.RedisCommon;
import com.clt.framework.utils.PropertiesReader;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by liujinbang on 15/12/12.
 * 	
 */
public class RedisExecute extends BaseRedisDao {


    private ShardedJedisPool pool = null;


    private String configFilePath = null;


    private final static Integer DEFAULT_MAX_ACTIVE = 500;
    private final static Integer DEAULT_MAX_WAIT_MILLIS = 1000 * 10;
    private final static Integer DEAULT_MAX_IDLE = 10;
    private final static boolean DEAULT_TEST_ON_BORROW = true;


    protected void setConfigFilePath(String configFilePaths) {
        configFilePath = configFilePaths;
    }

    /**
     * 构建redis连接池
     *
     * @return JedisPool
     */
    public ShardedJedisPool getPool() throws FrameworkException {

        if (RedisCommon.stringRedisExecuteMap != null && RedisCommon.stringRedisExecuteMap.containsKey(configFilePath)) {
            pool = RedisCommon.stringRedisExecuteMap.get(configFilePath);
        }

        if (pool == null) {
          /*  JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(500);

            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
           // config.setMaxWait();
            config.setMaxWaitMillis(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, "192.168.2.191", 8888);

            */


            /**
             * 校验是否为空
             */
            if (configFilePath == null || configFilePath.isEmpty()) {
                throw new FrameworkException("properity configFilePath can not be null!");
            }

            Properties properties = PropertiesReader.getProperties(configFilePath);

            if (properties == null || properties.isEmpty()) {
                throw new FrameworkException("can not find file " + configFilePath + ".properties!");
            }

            //   GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            JedisPoolConfig config = new JedisPoolConfig();//Jedis池配置
            String maxActive = properties.getProperty("redis.pool.maxActive");

            if (maxActive != null && !maxActive.trim().isEmpty()) {
                config.setMaxTotal(Integer.parseInt(maxActive.trim()));
            } else {
                config.setMaxTotal(DEFAULT_MAX_ACTIVE);
            }

            String maxWait = properties.getProperty("redis.pool.maxWait");
            if (maxWait != null && !maxWait.trim().isEmpty()) {
                config.setMaxWaitMillis(1000 * Integer.parseInt(maxActive.trim()));
            } else {
                config.setMaxWaitMillis(DEAULT_MAX_WAIT_MILLIS);
            }

            String maxIdle = properties.getProperty("redis.pool.maxIdle");
            if (maxIdle != null && !maxIdle.trim().isEmpty()) {
                config.setMaxIdle(Integer.parseInt(maxIdle));
            } else {
                config.setMaxIdle(DEAULT_MAX_IDLE);
            }


            String testOnBorrow = properties.getProperty("redis.pool.testOnBorrow");

            if (testOnBorrow != null && !testOnBorrow.trim().isEmpty()) {
                config.setTestOnBorrow(Boolean.valueOf(testOnBorrow.trim()));
            } else {
                config.setTestOnBorrow(DEAULT_TEST_ON_BORROW);
            }


            //    Set<HostAndPort> nodes = new HashSet<HostAndPort>();
            List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>();
            String hosts_config = properties.getProperty("redis.hostname");


            if (hosts_config == null || hosts_config.trim().isEmpty()) {
                throw new FrameworkException("there is no redis.hostname property in file " + configFilePath + ".properties !");
            }
            String ports_config = properties.getProperty("redis.port");
            if (ports_config == null || ports_config.trim().isEmpty()) {
                throw new FrameworkException("there is no redis.port property in file " + configFilePath + ".properties !");
            }

            String[] hosts = hosts_config.split(",");
            String[] ports = ports_config.split(",");

            if (hosts.length != ports.length) {
                throw new FrameworkException("error occured when deal redis.hostname and redis.port property  in  file " + configFilePath + ".properties !");
            }

            for (int i = 0; i < hosts.length; i++) {
                JedisShardInfo infoA = new JedisShardInfo(hosts[i], Integer.parseInt(ports[i].trim()));
                // HostAndPort hostAndPort = new HostAndPort(hosts[i], Integer.parseInt(ports[i].trim()));
                jdsInfoList.add(infoA);
                /**
                 * if password need
                 */

            }

            

            
            /*
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            shards.add(new JedisShardInfo("127.0.0.1", 6379));
            shards.add(new JedisShardInfo("127.0.0.1", 6380));
            */

            //sjt20151212
            pool = new ShardedJedisPool(config, jdsInfoList);

            // public JedisCluster(Set<HostAndPort> jedisClusterNode,
            // int connectionTimeout, int soTimeout,
            // int maxRedirections, GenericObjectPoolConfig poolConfig) {

            //   pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

            RedisCommon.stringRedisExecuteMap.put(configFilePath,pool);


        }
        return pool;
    }


    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public String get(String key) throws FrameworkException {
        String value = null;
        getPool();
        try {
            value = pool.getResource().get(key);
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        } finally {
            //返还到连接池
        }

        return value;
    }


    public String set(String key, String value) throws FrameworkException {


        return set(key, value, null, null);
    }

    /**
     * 保存数据
     *
     * @param key
     * @return
     */
    public String set(String key, String value, TimeUnit timeUnit, Integer expireTime) throws FrameworkException {

        getPool();
        String msg = "";
        try {
            if (timeUnit != null) {
                pool.getResource().set(key, value);
                pool.getResource().expire(key, Integer.valueOf(timeUnit.toSeconds(expireTime)+""));
            } else {
                pool.getResource().set(key, value);
            }
        } catch (Exception e) {
            //释放redis对象
            //pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            //  returnResource(pool, jedis);
        }

        return msg;
    }


    /**
     * 获取自增字段
     * @param key
     * @return
     * @throws FrameworkException 
     */
    public Long getInCrease(String key) throws FrameworkException {
        return this.getInCrease(key, 1);
    }


    /**
     * 获取自增字段
     * @param key
     * @return
     */
    public Long getInCrease(String key, Integer increase) throws FrameworkException {
        getPool();
        return pool.getResource().incrBy(key, increase);
    }




}
