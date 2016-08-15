package com.clt.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liujinbang on 15/9/1.
 *
 * 读取properities方法
 *
 */
public class PropertiesReader {

    //private static final Logger CLASS_LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
    private static Map<String, Properties> propertiesMap = new ConcurrentHashMap();
    private static final String DEFAULT = "_default";

    private PropertiesReader() {
    }

    public static Properties getProperties(String propertiesName) {
        return getProperties(propertiesName, true);//默认从缓存中获取
    }

    public static Properties getProperties(String propertiesName, boolean isCache) {
        Properties resultProperties = (Properties)propertiesMap.get(propertiesName);
        if(resultProperties == null) {
            Properties defaultProp = createProperties(propertiesName + DEFAULT);
            resultProperties = createProperties(propertiesName);
            if(defaultProp != null && resultProperties != null) {
                resultProperties.putAll(defaultProp);
            } else if(defaultProp != null) {
                resultProperties = defaultProp;
            }

            if(isCache && resultProperties != null) {
                propertiesMap.put(propertiesName, resultProperties);
            }
        }

        return resultProperties;
    }

    public static Properties createProperties(String propertiesName) {
        InputStream fis = null;
        Properties properties = null;

        try {
            fis = PropertiesReader.class.getResourceAsStream("/" + propertiesName + ".properties");
           // fis = PropertiesReader.class.getResourceAsStream(propertiesName + ".properties");
            if(fis != null) {
                properties = new Properties();
                properties.load(fis);
                fis.close();
            }
        } catch (Exception var4) {
         //   CLASS_LOGGER.error(var4.getMessage(), var4);
            try {
                fis.close();
                fis = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return properties;
    }


}
