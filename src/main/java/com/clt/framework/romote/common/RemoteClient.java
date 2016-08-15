package com.clt.framework.romote.common;

import com.clt.base.exception.FrameworkException;
import com.clt.framework.RemoteType;
import com.clt.framework.romote.Executor.FtpExecutors.FtpClient4Jdk7plus;
import com.clt.framework.romote.Executor.HttpExecutors.HttpExecute;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujinbang on 15/8/29.
 */
public class RemoteClient {

    RemoteType remoteType;
    /**
     * remoteClient 构造器
     *
     */
     public RemoteClient(RemoteType remoteType){
         this.remoteType = remoteType;
     }


    /**
     * 调用方法  请求远程服务
     * @param remoteClientDefaultParms
     *
     *        如果是FTP请求 直接返回 ftpClient
     *                      returnParm.put("ftpClient", new FTPExecute());
     * @return
     */
    public  Map<String,Object> executeObjects(RemoteClientDefaultParms remoteClientDefaultParms) throws  Exception{


        Map<String,Object> returnParm = new HashMap<String, Object>();
        //Object o = new Object();
        /**
         * 判断参数是否为空
         */

        if (remoteClientDefaultParms == null){
            throw new FrameworkException("调用参数remoteClientDefaultParms参数不能为空！");
        }

        //URL 是否为空
        if (remoteClientDefaultParms.getUrl() == null || remoteClientDefaultParms.getUrl().trim().isEmpty()){
            throw new FrameworkException("url 参数不能为空！");
        }//URL 是否为空

        if (this.remoteType.equals(RemoteType.HTTP)){

            HttpExecute httpExecute = new HttpExecute(remoteClientDefaultParms.getCharset());



            if (remoteClientDefaultParms.getRequestMethod() != null){

                if (remoteClientDefaultParms.getRequestMethod().equals(RequestMethod.GET)){

                    HttpURLConnection conn = (HttpURLConnection)
                            httpExecute.sendGetRequest(remoteClientDefaultParms.getUrl()  ,
                                    remoteClientDefaultParms.getParms(), remoteClientDefaultParms.getHttpHeaders());
                    int code = conn.getResponseCode();

                    InputStream in = conn.getInputStream();

                    returnParm.put("responseCode" , code);
                    returnParm.put("responseInputStream",in);

                }else if (remoteClientDefaultParms.getRequestMethod().equals(RequestMethod.POST)){
                    HttpURLConnection conn = (HttpURLConnection) httpExecute.sendPostRequest(remoteClientDefaultParms.getUrl(),
                            remoteClientDefaultParms.getParms(), remoteClientDefaultParms.getHttpHeaders());
                    int code = conn.getResponseCode();

                    InputStream in = conn.getInputStream();

                    returnParm.put("responseCode" , code);
                    returnParm.put("responseInputStream", in);

                }else{
                    throw  new FrameworkException("不支持 "+remoteClientDefaultParms.getRequestMethod().name()+" 请求方式！");
                }
            }else  if(remoteClientDefaultParms.getFiles() != null && remoteClientDefaultParms.getFiles().length > 0){

                InputStream inputStream = httpExecute.uploadFiles(remoteClientDefaultParms.getUrl(), remoteClientDefaultParms.getParms(), remoteClientDefaultParms.getFiles());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
               int code = -1 ;
                if (reader.readLine().indexOf("200") > -1) {//读取web服务器返回的数据，判断请求码是否为200，如果不是200，代表请求失败
                    code = 200;
                }else if (reader.readLine().indexOf("404")  > -1){
                    code = 404;
                }else  if (reader.readLine().indexOf("500")  > -1){
                    code = 500;
                }else  if (reader.readLine().indexOf("503")  > -1){
                    code = 503;
                }
                returnParm.put("responseCode" , code);
                returnParm.put("responseInputStream", inputStream);

                reader.close();

            }else{
                throw  new FrameworkException("requestMethod参数不能为空");
            }
        }else if (this.remoteType.equals(RemoteType.FTP)){

            /**
             * 如果是FTP请求 直接返回FTP
             */
            returnParm.put("ftpClient", new FtpClient4Jdk7plus());

        }else if (this.remoteType.equals(RemoteType.WEBSERVICE)){
                throw  new FrameworkException("暂不支持WebService请求");
        }else if (this.remoteType.equals(RemoteType.RFC)){
            throw  new FrameworkException("暂不支持RFC请求");
        }



        return   returnParm;
    }

}
