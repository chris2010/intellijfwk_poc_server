package com.clt.weixin.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WeiXinPropertiesUtil {
	private static Properties properties = new Properties();
	private static String appId;
	private static String appSecret;
	private static String token;
	private static String merchantNo;
	private static String partnerKey;
	private static String accessToken;
	private static String accessExpires;
	
	private static String path = Thread.currentThread().getContextClassLoader().getResource("weixin.properties").getPath().replace("%20", " ");

	static {
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
			properties.load(in);
			appId = properties.getProperty("AppId");
			appSecret = properties.getProperty("AppSecret");
			token = properties.getProperty("Token");
			merchantNo = properties.getProperty("MerchantNo");
			partnerKey = properties.getProperty("PartnerKey");
			accessToken = properties.getProperty("AccessToken");
			accessExpires = properties.getProperty("AccessExpires");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getAppId() {
		return appId;
	}

	public static void setAppId(String appId) {
		WeiXinPropertiesUtil.appId = appId;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		WeiXinPropertiesUtil.token = token;
	}

	public static String getAppSecret() {
		return appSecret;
	}

	public static void setAppSecret(String appSecret) {
		WeiXinPropertiesUtil.appSecret = appSecret;
	}

	public static String getMerchantNo() {
		return merchantNo;
	}

	public static void setMerchantNo(String merchantNo) {
		WeiXinPropertiesUtil.merchantNo = merchantNo;
	}

	public static String getPartnerKey() {
		return partnerKey;
	}

	public static void setPartnerKey(String partnerKey) {
		WeiXinPropertiesUtil.partnerKey = partnerKey;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		WeiXinPropertiesUtil.accessToken = accessToken;
	}

	public static String getAccessExpires() {
		return accessExpires;
	}

	public static void setAccessExpires(String accessExpires) {
		WeiXinPropertiesUtil.accessExpires = accessExpires;
	}

	public static void setProperties(String key, String value) {
		properties.setProperty(key, value);

		try {
			FileOutputStream out = new FileOutputStream(path);
			properties.store(out, "kehwa(C)Right");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
