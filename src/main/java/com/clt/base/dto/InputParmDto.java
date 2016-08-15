package com.clt.base.dto;

import java.io.Serializable;


/**
 * 输入参数公共类
 * @author liujinbang
 *
 * @param <T>
 */
public class InputParmDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7099367400135083598L;
	private T t;
	private String key; // 本次参数或者请求的唯一值 ，防止重复提交
	private Integer isUnique;// 是否唯一请求， 1 是 ，如果是1，则根据key值判断当前是否请求过

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Integer isUnique) {
		this.isUnique = isUnique;
	}

}
