package com.stylefeng.guns.rest.modular.auth.enums;

import lombok.Getter;

/**
* create by guanqing
* 2018年7月19日 下午9:37:24
*/
@Getter
public enum RegisterType {
	// 手机登录
	TELEPHONE_TYPE(1),
	// 微信登录
	WECHAT_TYPE(2),
	// QQ登录
	TECENT_TYPE(3);
	
	private Integer type;
	
	RegisterType(int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	public static RegisterType getRegisterType(Integer type) {
		for (RegisterType register : RegisterType.values()) {
			if (register.getType().equals(type))
				return register;
		}
		return null;
	}
}
