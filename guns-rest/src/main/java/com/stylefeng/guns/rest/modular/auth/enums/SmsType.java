package com.stylefeng.guns.rest.modular.auth.enums;

import lombok.Getter;

/**
* create by guanqing
* 2018年7月19日 下午2:36:25
*/
@Getter
public enum SmsType {
	
	// 注册
	REGISTER_TYPE(1),
	// 登录
	LOGIN_TYPE(2),
	// 修改密码
	MODIFY_TYPE(3);

	private Integer type;
	
	SmsType(int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	public static SmsType getSmsType(Integer type) {
		for (SmsType sms : SmsType.values()) {
			if (sms.getType().equals(type))
				return sms;
		}
		return null;
	}
}
