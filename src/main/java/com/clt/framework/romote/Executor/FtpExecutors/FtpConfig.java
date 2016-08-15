package com.clt.framework.romote.Executor.FtpExecutors;

/**
 * Created by liujinbang on 15/8/30.
 * <p>
 * FTP工具类
 */
public class FtpConfig {

    private String server;//服务器地址
    private int port = 21;
    private String username;//用户名
    private String password;//密码
    private String location = "/";//默认工作地址


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
