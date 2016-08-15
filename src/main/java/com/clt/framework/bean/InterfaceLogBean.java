package com.clt.framework.bean;

import java.io.Serializable;
import java.util.Date;

public class InterfaceLogBean implements Serializable {


	//ID   调用时间 输入参数  返回参数  返回时间   调用人  token  成功标识  服务名称(URL)  设备信息  调用节点  经纬度信息（微信端） IP地址
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5618552339041403289L;

	private  String id;
	
	/*
	 * 调用时间
	 */
	private Date beginTime;
	/*
	 * 输入参数
	 */
	private String inputParm;
	
	/*
	 * 返回参数
	 */
	private String returnParm;

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
	 *成功标识 
	 */
	private String resultFlag;
	
	/*
	 *服务地址 
	 */
	private String serviceAddress;
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

	public String getInputParm() {
		return inputParm;
	}

	public void setInputParm(String inputParm) {
		this.inputParm = inputParm;
	}

	public String getReturnParm() {
		return returnParm;
	}

	public void setReturnParm(String returnParm) {
		this.returnParm = returnParm;
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

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
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
