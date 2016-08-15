package com.clt.base.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by liujinbang on 15/8/26.
 */
public class BaseBean  implements Serializable{

    private Integer createEmp;
    private Date createTime;
    private Integer modifyEmp;
    private Date modifyTime;

    public Integer getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Integer createEmp) {
        this.createEmp = createEmp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Integer modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
