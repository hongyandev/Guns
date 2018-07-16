package com.stylefeng.guns.rest.modular.auth.enums;

import lombok.Getter;
/**
* create by guanqing
* 2018年7月13日 下午3:12:31
*/
@Getter
public enum AuthType {

	// 密码登录
	PASSWORD_TYPE(1),
	// 短信登录
	SMS_TYPE(2),
	// 其他登录
	OTHER_TYPE(3);
	
	private Integer type;
	
	AuthType(int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	public static AuthType getAuthType(Integer type) {
		for (AuthType auth : AuthType.values()) {
			if (auth.getType().equals(type))
				return auth;
		}
		return null;
	}
}
