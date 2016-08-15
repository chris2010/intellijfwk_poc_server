package com.clt.framework.logAppender;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.bson.Document;

import com.clt.base.exception.FrameworkException;
import com.clt.framework.bean.InterfaceLogBean;
import com.clt.framework.bean.PVLogBean;
import com.clt.framework.utils.BeanUtils;
import com.clt.framework.utils.MongoManager;
import com.mongodb.client.MongoDatabase;

public class MongoAppender extends AppenderSkeleton {

	private String hostname;
	private String port;
	private String databaseName;
	private String collectionName;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	
	
	
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void append(LoggingEvent arg0) {
		// TODO Auto-generated method stub

		try {
			String curCollection = "systemLogs";
			Object object = arg0.getMessage();

			if (object != null) {
				if (object instanceof InterfaceLogBean) {
					curCollection = collectionName.split(",")[0];
				} else if (object instanceof PVLogBean) {
					curCollection = collectionName.split(",")[1];
				} else {
					return;
				}

			} else {
				return;

			}

			MongoDatabase db = MongoManager.getDB(databaseName);
			//MongoCollection collection = ;
			try {
				
					Document doc=new Document();  
		            //insert key  
					
					/**
					 * 将对象转换成Map
					 */
					Map<String,Object> map = BeanUtils.convertBean2Map(object);
					
					/**
					 * 将Map转换成document
					 */
					Iterator<String> iterator = map.keySet().iterator();
					
					while(iterator.hasNext()){
						String iterKey = iterator.next();
						doc.put(iterKey, map.get(iterKey));
					}
				  //////////转换结束///////////
					
				db.getCollection(curCollection).insertOne(doc);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (FrameworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// arg0.get
	}

}
