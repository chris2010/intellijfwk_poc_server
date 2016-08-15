package com.clt.framework.common;

import com.clt.framework.romote.Executor.RedisExcutors.RedisExecute;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujinbang on 15/12/12.
 */
public class RedisCommon {

    public  static Map<String, ShardedJedisPool> stringRedisExecuteMap = new HashMap<String, ShardedJedisPool>();

}
