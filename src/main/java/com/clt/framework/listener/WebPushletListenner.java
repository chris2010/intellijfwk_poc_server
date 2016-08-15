package com.clt.framework.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.clt.framework.utils.PropertiesReader;
/*import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;*/


/**
 * Created by liujinbang on 15/10/1.
 */
public class WebPushletListenner implements ServletContextListener {

    //Map<String, CometEngine> map = new HashMap<String, CometEngine>();

    //private static final String CHANNEL = "publicPublish";
    public void contextInitialized(ServletContextEvent arg0) {
/*        CometContext cc = CometContext.getInstance();
        Properties properties
                = PropertiesReader.createProperties("pushletChannels");

        for (Map.Entry<Object, Object> e : properties.entrySet()) {
            if (e != null && e.getValue() != null && !e.toString().trim().isEmpty()) {
                cc.registChannel(e.getValue().toString());
            }
        }*/


        // cc.registChannel(CHANNEL);//注册当前 channel
        //   cc.registChannel("chat");
        /*
        Thread helloAppModule = new Thread(new HelloAppModule(), "publicPublish");
        helloAppModule.setDaemon(true);
      //  helloAppModule.setName("publicPublish");
        helloAppModule.start();
        Thread helloAppModule2 = new Thread(new HelloAppModule(), "public");
        helloAppModule2.setDaemon(true);
        //  helloAppModule.setName("publicPublish");
        helloAppModule2.start();
            */
    }


    /**
     * 每 10000ms发送一次消息 测试用
     */
/*    class HelloAppModule implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //System.out.println(Thread.currentThread().getName());
                String threadName = Thread.currentThread().getName();
                Runtime rt = Runtime.getRuntime();
                long total = rt.totalMemory();
                long free = rt.freeMemory();
                CometEngine engine1 = null;
                if (map.containsKey("threadName")) {
                    engine1 = map.get("threadName");
                } else {
                    engine1 = CometContext.getInstance().getEngine();
                }
                engine1.sendToAll(threadName, total + threadName + free);
            }
        }
    }*/

    public void contextDestroyed(ServletContextEvent arg0) {

    }

}
