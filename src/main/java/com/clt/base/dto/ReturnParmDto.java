package com.clt.base.dto;

/**
 * Created by liujinbang on 15/8/29.
 * <p/>
 * 返回参数实体类
 */
public class ReturnParmDto<T> {

    /*返回实体类*/
    private T t; //返回实体
    /*处理状态*/
    private Integer status;//处理状态
    /*错误信息,用户友好信息*/
    private String msg;//错误信息 用户友好信息


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
