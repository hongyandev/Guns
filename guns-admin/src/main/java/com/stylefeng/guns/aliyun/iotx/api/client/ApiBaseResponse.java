package com.stylefeng.guns.aliyun.iotx.api.client;

import java.util.Map;

public class ApiBaseResponse extends BaseResponse<ApiBaseResponse> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
