package com.clt.framework.utils;

import java.net.UnknownHostException;
import java.util.Properties;

import com.clt.base.exception.FrameworkException;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * mongoDB 连接池
 * 
 * @author liujinbang
 *
 */
public class MongoManager {

	private static MongoClient mongo = null;

	private MongoManager() {

	}

	/**
	 * 根据名称获取DB，相当于是连接
	 * 
	 * @param dbName
	 * @return
	 * @throws FrameworkException
	 */
	public static MongoDatabase getDB(String dbName) throws FrameworkException {
		if (mongo == null) {
			// 初始化
			init();
		}
		return mongo.getDatabase(dbName);
	}

	private static MongoClientOptions getConfOptions() {
		Properties properties = PropertiesReader.createProperties("log4j");

		properties = (properties == null ? PropertiesReader
				.createProperties("log4j_framework") : properties);
		int poolSize = new Integer(
				properties.getProperty("log4j.appender.MongoDB.poolSize"));// 连接数量
		int blockSize = new Integer(
				properties.getProperty("log4j.appender.MongoDB.blockSize")); // 等待队列长度
		return new MongoClientOptions.Builder().socketKeepAlive(true) // 是否保持长链接
				.connectTimeout(5000) // 链接超时时间
				.socketTimeout(5000) // read数据超时时间
				.readPreference(ReadPreference.primary()) // 最近优先策略
				// .autoConnectRetry(false) // 是否重试机制
				.connectionsPerHost(poolSize) // 每个地址最大请求数
				.maxWaitTime(1000 * 60 * 2) // 长链接的最大等待时间
				.threadsAllowedToBlockForConnectionMultiplier(blockSize) // 一个socket最大的等待请求数
				// .writeConcern(WriteConcern.NORMAL)
				.build();
	}

	/**
	 * 初始化连接池，设置参数。
	 * 
	 * @throws FrameworkException
	 */
	private static void init() throws FrameworkException {
		String confFilePath = "";

		Properties properties = PropertiesReader.createProperties("log4j");

		properties = (properties == null ? PropertiesReader
				.createProperties("log4j_framework") : properties);

		// ConfTool conf = new ConfTool(confFilePath);
		String host = properties.getProperty("log4j.appender.MongoDB.hostname");// //
																				// 主机名
		int port = new Integer(
				properties.getProperty("log4j.appender.MongoDB.port"));// 端口

		// 其他参数根据实际情况进行添加
		try {

			mongo = new MongoClient(new ServerAddress(host, port),
					getConfOptions());
			// mongo.setReadPreference(readPreference);
			// mongo.
			// MongoDatabase db = mongoClient.getDatabase("census");
			// mongo = new Mongo(host, port);
			// MongoOptions opt = mongo.getMongoOptions();

			// opt.connectionsPerHost = poolSize;
			// opt.threadsAllowedToBlockForConnectionMultiplier = blockSize;
		} catch (MongoException e) {
			// log error
			throw new FrameworkException("init mongodb connection pool fail!",
					e);
		}

	}

}
