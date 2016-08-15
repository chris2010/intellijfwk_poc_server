JDK版本要求:  jdk 7 or above
tomcat :    tomcat  7+
在Tomacat7的context.xml文件里的<Context>中加上<Loader delegate="true" />
开发时将业务数据放到com.clt.web目录中，切忌在base、framework中写业务代码

如果使用web端推送（否则在调用时，会出现405错误)，

            推送测试URL：http://IP:PORT/CONTEXTPATH/webpushlet.html

            将tomcat的 server.xml 中的 <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000"  redirectPort="8443" />
                 修改成： <Connector port="8080" protocol="org.apache.coyote.http11.Http11NioProtocol"
                	       connectionTimeout="20000"
                	       URIEncoding="UTF-8"
                	       useBodyEncodingForURI="true"
                	       enableLookups="false"
                	       redirectPort="8443" />
            并确认web.xml中有如下配置
                 <!--web pushlet推送-->
                  <listener>
                      <listener-class>org.comet4j.core.CometAppListener</listener-class>
                  </listener>
                  <servlet>
                      <display-name>CometServlet</display-name>
                      <servlet-name>CometServlet</servlet-name>
                      <servlet-class>org.comet4j.core.CometServlet</servlet-class>
                  </servlet>
                  <servlet-mapping>
                      <servlet-name>CometServlet</servlet-name>
                      <url-pattern>/pushlet</url-pattern>
                  </servlet-mapping>
                  <!--web pushlet推送  侦听配置结束-->
