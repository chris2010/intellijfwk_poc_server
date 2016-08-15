package com.clt.test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * 
 * @author songjiangtao
 *@date 2015年12月15日 下午2:04:39
 */
public class RedisPoolTest {
	//private static JedisPool pool;  
	private static ShardedJedisPool pool;  
	static {  
		ResourceBundle bundle = ResourceBundle.getBundle("redis_order_default");  
		if (bundle == null) {  
			throw new IllegalArgumentException("[redis_order_default.properties] is not found!");  
		}

		JedisPoolConfig config = new JedisPoolConfig();  
//111		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));  
	    config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));  
//111	    config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));  
	    config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));  
	    config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));  

	    
	    //pool = new JedisPool(config, bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port")));  
	    
	    
	    List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();  
	    JedisShardInfo jedisShardInfo1 = new JedisShardInfo(bundle.getString("redis1.ip"), Integer.valueOf(bundle.getString("redis.port")));  
	    //JedisShardInfo jedisShardInfo2 = new JedisShardInfo(bundle.getString("redis2.ip"), Integer.valueOf(bundle.getString("redis.port")));  
		list.add(jedisShardInfo1);  
		//list.add(jedisShardInfo2);  
		pool = new ShardedJedisPool(config, list); 
	
	}
	
	public static void main(String[] args) {
		
		//从池中获取一个Jedis对象  
		ShardedJedis jedis = pool.getResource();  
		String keys = "name";  
		String value = "snowolf1111111";  
		//删数据  
		jedis.del(keys);  
		//存数据  
		jedis.set(keys, value);  
		//取数据  
		String v = jedis.get(keys);  
		
		System.out.println(v);  
		
		//释放对象池  
		pool.returnResource(jedis);  
	}
}
