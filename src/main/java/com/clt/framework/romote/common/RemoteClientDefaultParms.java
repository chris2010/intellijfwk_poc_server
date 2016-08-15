package com.clt.framework.romote.common;

import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by liujinbang on 15/8/29.
 * <p/>
 * RemoteClient 请求远程服务 默认参数
 */
public class RemoteClientDefaultParms {


    /**
     * 请求最大超时时间
     */
    private Integer connectionTimeout = 100000;


    private Integer socketTimeout = 100000;

    private String url; //URL


    private String charset = Charset.forName("UTF-8").name();

    /**
     * 调用方式
     */
    private RequestMethod requestMethod = RequestMethod.POST;//默认调用方式

    /**
     * 请求重试次数
     */
    private Integer retryTimes = 1;

    /**
     * 输入参数  参数
     */
    private Map<String, String> parms;//

    /**
     * HTTP请求 文件
     */
    private FormFile[] files;

    /**
     * http headers
     */
    private Map<String, String> httpHeaders;//


    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Map<String, String> getParms() {
        return parms;
    }

    public void setParms(Map<String, String> parms) {
        this.parms = parms;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }


    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public FormFile[] getFiles() {
        return files;
    }

    public void setFiles(FormFile[] files) {
        this.files = files;
    }
}
