package com.stylefeng.guns.aliyun.iotx.api.client;

import lombok.Data;

import java.io.Serializable;

@Data
public class IoTApiResponse<T> implements Serializable{
	Integer code;
	String id;
	T data;
}
