package com.clt.framework.bean;

import java.io.Serializable;
import java.util.Date;

public class PVLogBean implements Serializable {

	// ID 调用人 调用时间 token 页面标识 来源页面 经纬度 设备信息 IP地址

	/**
	 * 
	 */
	private static final long serialVersionUID = -5618552339041403289L;

	private String id;

	/*
	 * 调用时间
	 */
	private Date beginTime;

	/*
	 * 返回时间
	 */
	private String returnTime;

	/*
	 * 调用人ID
	 */
	private String inputUserId;

	/*
	 * token信息
	 */
	private String token;

	/*
	 * 页面标识
	 */
	private String tokenFlag;
	/*
	 * 设备信息
	 */
	private String setInfo;

	/*
	 * 调用方
	 */
	private String inputFrom;

	/*
	 * 经度
	 */
	private String longitude;

	/*
	 * 纬度
	 */
	private String latitude;

	/*
	 * IP地址
	 */
	private String ipAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getInputUserId() {
		return inputUserId;
	}

	public void setInputUserId(String inputUserId) {
		this.inputUserId = inputUserId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenFlag() {
		return tokenFlag;
	}

	public void setTokenFlag(String tokenFlag) {
		this.tokenFlag = tokenFlag;
	}

	public String getSetInfo() {
		return setInfo;
	}

	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}

	public String getInputFrom() {
		return inputFrom;
	}

	public void setInputFrom(String inputFrom) {
		this.inputFrom = inputFrom;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
