package com.stylefeng.guns.rest.modular.auth.validator.dto;
/**
* create by guanqing
* 2018年7月19日 下午2:52:03
*/

import com.stylefeng.guns.rest.modular.auth.enums.SmsType;

public interface Smsdence {
	
	/**
	 * 移动设备
	 */
	String getSmsdenceName();
	
	/**
	 * 应用场景
	 */
	SmsType getSmsType();
}
