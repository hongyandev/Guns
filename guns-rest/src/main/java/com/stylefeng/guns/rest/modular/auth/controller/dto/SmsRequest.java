package com.stylefeng.guns.rest.modular.auth.controller.dto;

import com.stylefeng.guns.rest.modular.auth.enums.SmsType;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Smsdence;

/**
* create by guanqing
* 2018年7月19日 下午2:54:44
*/
public class SmsRequest implements Smsdence {
	
	private String telephone;
	private Integer smsType;

	@Override
	public String getSmsdenceName() {
		// TODO Auto-generated method stub
		return this.telephone;
	}

	@Override
	public SmsType getSmsType() {
		// TODO Auto-generated method stub
		return SmsType.getSmsType(this.smsType);
	}
}
