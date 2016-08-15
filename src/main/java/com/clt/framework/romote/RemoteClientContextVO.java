package com.clt.framework.romote;

import com.clt.framework.RemoteType;

/**
 * Created by liujinbang on 15/8/28.
 *
 *  远程服务VO
 *
 */
public class RemoteClientContextVO {

    private RemoteType remoteType;
    private String url;
    private int repeatCount;
    private String hashValue;

    public RemoteType getRemoteType() {
        return remoteType;
    }

    public void setRemoteType(RemoteType remoteType) {
        this.remoteType = remoteType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }
}
