package com.clt.test;

import com.clt.base.exception.FrameworkException;
import com.clt.framework.utils.MongoManager;

public class MongoDBTest {
	
	
	public static void main(String[] args) throws FrameworkException {
		
		MongoManager.getDB("123");
		
		
	}
	

}
