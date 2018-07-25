package com.stylefeng.guns.rest.core.domain;


/**
* create by guanqing
* 2018年7月12日 下午4:45:35
*/
public interface Result<T> {
	
	/** 错误码. */
	Integer getCode();
	
	/** 提示信息. */
	String getMessage();
	
	/** 具体的内容. */
	T getData();
}
