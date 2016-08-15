package com.clt.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
public class SpringRedisPoolTest {
	private ApplicationContext app;  
	private ShardedJedisPool pool;  
	  
	@Before  
	public void before() throws Exception {  
	    app = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-mybatis.xml"});  
	    pool = (ShardedJedisPool) app.getBean("shardedJedisPool");  
	}  
	  
	@Test  
	public void test() {  
	  
	    // 从池中获取一个Jedis对象  
	    ShardedJedis jedis = pool.getResource();  
	    String keys = "name";  
	    String value = "snowolf";  
	    // 删数据  
	    jedis.del(keys);  
	    // 存数据  
	    jedis.set(keys, value);  
	    // 取数据  
	    String v = jedis.get(keys);  
	  
	    System.out.println(v);  
	  
	    // 释放对象池  
	    pool.returnResource(jedis);  
	  
	    assertEquals(value, v);  
	}  
}
