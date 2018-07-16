package com.stylefeng.guns.aliyun.iotx.api.client;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public abstract class BaseResponse <T extends BaseResponse> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer code;
	String id;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
