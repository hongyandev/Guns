package com.stylefeng.guns.aliyun.iotx.api.client;

import java.util.Map;

public class AliyunResponse {
	Integer code;
	Map data;
	String id;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Map getData() {
		return data;
	}
	public void setData(Map data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
