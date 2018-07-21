package com.stylefeng.guns.rest.modular.auth.controller.dto;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
* create by guanqing
* 2018年7月20日 下午4:03:51
*/
@Data
public class TokenRequest {

	@NotBlank(message="参数userId为空")
	private String userId;
	
	@NotBlank(message="参数token为空")
	private String token;
}
