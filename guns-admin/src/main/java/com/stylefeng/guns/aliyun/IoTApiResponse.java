package com.stylefeng.guns.aliyun;

import lombok.Data;

import java.io.Serializable;

@Data
public class IoTApiResponse<T> implements Serializable{
	Integer code;
	String id;
	T data;
}
