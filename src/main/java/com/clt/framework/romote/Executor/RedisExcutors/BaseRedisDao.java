package com.clt.framework.romote.Executor.RedisExcutors;

import java.util.concurrent.TimeUnit;

import com.clt.base.exception.FrameworkException;

import redis.clients.jedis.ShardedJedisPool;

public abstract class BaseRedisDao {
	
	
	//protected void setConfigFilePath(String configFilePaths);

    /**
     * 构建redis连接池
     *
     * @return JedisPool
     */
    public abstract ShardedJedisPool getPool() throws FrameworkException ;


    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public abstract  String get(String key) throws FrameworkException ;


    public abstract  String set(String key, String value) throws FrameworkException  ;
    /**
     * 保存数据
     *
     * @param key
     * @return
     */
    public abstract  String set(String key, String value, TimeUnit timeUnit, Integer expireTime) throws FrameworkException ;


    /**
     * 获取自增字段
     * @param key
     * @return
     * @throws FrameworkException 
     */
    public abstract  Long getInCrease(String key) throws FrameworkException ;


    /**
     * 获取自增字段
     * @param key
     * @return
     */
    public abstract  Long getInCrease(String key, Integer increase) throws FrameworkException;

}
