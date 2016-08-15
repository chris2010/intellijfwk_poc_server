package com.clt.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.clt.weixin.util.HttpKit;

public class GetJiamiTest {
	private static String URL = "http://api.ly-demo.wanda.cn/PostTest/GetJson";
	private static String URLTest = "http://api.ly-demo.wanda.cn/PostTest/Post";
	private static String URLDECODY = "http://api.ly-demo.wanda.cn//PostTest/DecBody";
public static void main(String[] args) throws Exception{
//	 String params="{'body': {'dictionaryCategory': 'MakeProjectType'}}";
	 Map<String, String> params = new HashMap<String, String>();
	 params.put("PartnerId", "ff9b3b20-7eec-11e5-a8db-f8bc12");
	 params.put("Function", "GetDestination");
	 params.put("Body", "{'destinationDate': '2015-10-01 21:09:33.143','pageSize': '50'}");
	 params.put("Key", "U2FsdGVkX19lh/5hsMhQrQVkkGxn+93D");
	 String result = HttpKit.post(URL, params);
//	 System.out.println(result);
	 
	 
	 Map<String, String> paramsTest = new HashMap<String, String>();
	 paramsTest.put("form", result);
	 String resultTest = HttpKit.post(URLTest, paramsTest);
//	 System.out.println(resultTest);
	 
//	 to
	 Map<String, String> paramsDecode = new HashMap<String, String>();
	 paramsDecode.put("to", resultTest);
	 paramsDecode.put("key", "U2FsdGVkX19lh/5hsMhQrQVkkGxn+93D");
	 String resultDecode = HttpKit.post(URLDECODY, paramsDecode);
	 System.out.println(resultDecode);
	 
}
}
