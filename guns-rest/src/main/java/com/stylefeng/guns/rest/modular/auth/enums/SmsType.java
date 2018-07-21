package com.stylefeng.guns.rest.modular.auth.enums;

import lombok.Getter;

/**
* create by guanqing
* 2018年7月19日 下午2:36:25
*/
@Getter
public enum SmsType {
	
	// 注册
	REGISTER_TYPE(1, "SMS_139976382", "{\"code\":\"{0}\"}"),
	// 登录
	LOGIN_TYPE(2, "SMS_139986222", "{\"code\":\"{0}\"}"),
	// 修改密码
	MODIFY_TYPE(3, "SMS_139976396", "{\"code\":\"{0}\"}");

	private Integer type;
	private String templateCode;
	private String template;
	
	SmsType(int type, String templateCode, String template) {
		this.type = type;
		this.templateCode = templateCode;
		this.template = template;
	}
	
	public static SmsType getSmsType(Integer type) {
		for (SmsType sms : SmsType.values()) {
			if (sms.getType().equals(type))
				return sms;
		}
		return null;
	}
}
