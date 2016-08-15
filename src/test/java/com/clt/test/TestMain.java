package com.clt.test;

import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;
import java.util.UUID;

/**
 * Created by liujinbang on 15/9/12.
 */
public class TestMain {

    @Test
    public void main(){

        String url = "http://yxdqdcj.wandafilm-dev.com";
        url = "http://10.1.191.83:80";

        String uuid = UUID.randomUUID().toString();

        System.out.println(uuid);

        try{

            System.out.println(11111);
            URL localURL = new URL(url+"/WeChat/vxin/luckyDraw?" +
                    "activityid=202&openid=18800001111");
            URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;

            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;

            if (httpURLConnection.getResponseCode() == 200) {
                // throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }else{

            }

        }catch(Exception e){

        }




        /** 刮奖动作  */
        try {
            String strURL = url + "/WeChat/vxin/queryPrizes";
            String params = "{\"activities_detail_id\":\"3\",\"prize_type\":\"0\",\"openid\":'"+uuid+"'}";
            System.out.println(22222);
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;
            InputStream inputStream = null;
            StringBuffer responseResult = new StringBuffer();



            URL realUrl = new URL(strURL);
            // 打开和URL之间的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write("content="+URLEncoder.encode(params.toString(), "UTF-8"));
            // flush输出流的缓冲
            printWriter.flush();
            // 根据ResponseCode判断连接是否成功
            int responseCode = httpURLConnection.getResponseCode();


            if (responseCode != 200) {
                System.out.println(" Error===" + responseCode);
            } else {
                System.out.println(" Post Success!===" + responseCode);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {



        }
/**g刮奖结束   */

        /** 填写中奖信息动作  */
        try {
            String strURL = url + "/WeChat/vxin/queryPrizes";
            String params = "{\"activities_detail_id\":\"3\",\"prize_type\":\"0\"}";
            System.out.println(333333);
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;
            InputStream inputStream = null;
            StringBuffer responseResult = new StringBuffer();



            URL realUrl = new URL(strURL);
            // 打开和URL之间的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write("content="+ URLEncoder.encode(params.toString(), "UTF-8"));
            // flush输出流的缓冲
            printWriter.flush();
            // 根据ResponseCode判断连接是否成功
            int responseCode = httpURLConnection.getResponseCode();


            if (responseCode != 200) {
                System.out.println(" Error===" + responseCode);
            } else {
                System.out.println(" Post Success!===" + responseCode);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {



        }

        /**填写中奖信息动作结束*/


    }


    @Test
    public  void  test02(){


        String url = "http://yxdqdcj.wandafilm-dev.com";
           url = "http://10.1.191.158:80";
        String uuid = UUID.randomUUID().toString().replace("-","");


        try{
           // lr.start_transaction("stepone");

            URL localURL = new URL(url+"/WeChat/vxin/luckyDraw?" +
                    "activityid=202&openid="+uuid);
            URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;

            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;

            if (httpURLConnection.getResponseCode() == 200) {
                // throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
              //  lr.end_transaction("stepone", lr.PASS);
            }else{
             //   lr.message( "stepone fail "+ httpURLConnection.getResponseCode() );
             //   lr.end_transaction("stepone", lr.FAIL);
            }

        }catch(Exception e){
           // lr.end_transaction("stepone", lr.FAIL);
        }



        try{
            // lr.start_transaction("stepone");

            URL localURL = new URL(url+"/WeChat/vxin/toLuckyDraw?openid="+uuid);
            URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;

            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");

            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;

            if (httpURLConnection.getResponseCode() == 200) {

                //  lr.end_transaction("steptwo", lr.PASS);
            }else{
                //   lr.message( "steptwo fail "+ httpURLConnection.getResponseCode() );
                //   lr.end_transaction("steptwo", lr.FAIL);
            }

        }catch(Exception e){
            // lr.end_transaction("steptwo", lr.FAIL);
        }



        Integer scope  = new Random().nextInt(100);

       // if (scope % 2 == 0){
        /** 1??¡À?¡¥¡Á¡Â  */
        try {
          //  lr.start_transaction("stepthree");
            String strURL = url + "/WeChat/vxin/queryPrizes?openid="+uuid;
            // String params = "{\"activities_detail_id\":\"3\",\"prize_type\":\"0\"}";
            String params = "{\"activities_detail_id\":\"161\",\"prize_type\":1,\"name\":\"test\",\"phone\":\"66666666666\",\"address\":\"ceshi\",\"isUpdate\":1}";

            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;
            InputStream inputStream = null;
            StringBuffer responseResult = new StringBuffer();



            URL realUrl = new URL(strURL);
            // ¡ä¨°?ao¨ªURL????¦Ì?¨¢??¨®
            HttpURLConnection httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // ¨¦¨¨??¨ª¡§¨®?¦Ì????¨®¨º?D?
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
            // ¡¤¡é?¨ªPOST???¨®¡À?D?¨¦¨¨??¨¨???¨¢?DD
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // ??¨¨?URLConnection???¨®??¨®|¦Ì?¨º?3?¨¢¡Â
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // ¡¤¡é?¨ª???¨®2?¨ºy
            printWriter.write("content="+URLEncoder.encode(params.toString(), "UTF-8"));
            // flush¨º?3?¨¢¡Â¦Ì??o3?
            printWriter.flush();
            // ?¨´?YResponseCode?D??¨¢??¨®¨º?¡¤?3¨¦1|
            int responseCode = httpURLConnection.getResponseCode();


            if (responseCode == 200) {
                //  System.out.println(" Error===" + responseCode);
             //   lr.end_transaction("stepthree", lr.PASS);
            } else {
                //  System.out.println(" Post Success!===" + responseCode);
             //   lr.message( "stepthree fail "+responseCode );
             //   lr.end_transaction("stepthree", lr.FAIL);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          //  lr.end_transaction("steptwo", lr.FAIL);
        }
/**g1??¡À?¨¢¨º?   */
    //    }else {
            /** ¨¬?D¡ä?D?¡ÀD??¡é?¡¥¡Á¡Â  */
            try {
                // lr.start_transaction("stepfour");
                String strURL = url + "/WeChat/vxin/queryPrizes?openid="+uuid;
                // String params = "{\"activities_detail_id\":\"3\",\"prize_type\":\"0\"}";
                String params = "{\"activities_detail_id\":\"161\",\"prize_type\":\"1\"}";
              //  System.out.println(333333);
                InputStreamReader inputStreamReader = null;
                BufferedReader reader = null;
                StringBuffer resultBuffer = new StringBuffer();
                String tempLine = null;
                InputStream inputStream = null;
                StringBuffer responseResult = new StringBuffer();


                URL realUrl = new URL(strURL);
                // ¡ä¨°?ao¨ªURL????¦Ì?¨¢??¨®
                HttpURLConnection httpURLConnection = (HttpURLConnection) realUrl.openConnection();
                // ¨¦¨¨??¨ª¡§¨®?¦Ì????¨®¨º?D?
                httpURLConnection.setRequestProperty("accept", "*/*");
                httpURLConnection.setRequestProperty("connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
                // ¡¤¡é?¨ªPOST???¨®¡À?D?¨¦¨¨??¨¨???¨¢?DD
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                // ??¨¨?URLConnection???¨®??¨®|¦Ì?¨º?3?¨¢¡Â
                PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                // ¡¤¡é?¨ª???¨®2?¨ºy
                printWriter.write("content=" + URLEncoder.encode(params.toString(), "UTF-8"));
                // flush¨º?3?¨¢¡Â¦Ì??o3?
                printWriter.flush();
                // ?¨´?YResponseCode?D??¨¢??¨®¨º?¡¤?3¨¦1|
                int responseCode = httpURLConnection.getResponseCode();


                if (responseCode == 200) {
                    // lr.end_transaction("stepfour",lr.PASS);
                } else {
                    // lr.message( "stepthree fail "+responseCode );
                    //  lr.end_transaction("stepfour",lr.FAIL);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                //   lr.end_transaction("stepfour",lr.FAIL);
            } finally {


            }
       // }

    }


}
