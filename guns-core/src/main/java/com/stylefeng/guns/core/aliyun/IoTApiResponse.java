package com.stylefeng.guns.core.aliyun;

import lombok.Data;

import java.io.Serializable;

@Data
public class IoTApiResponse<T> implements Serializable{
	private static final long serialVersionUID = -3752218064547962755L;
	
	Integer code;
	String id;
	T data;
}
