package com.stylefeng.guns.rest.modular.auth.controller.dto;

import lombok.Getter;

/**
* create by guanqing
* 2018年7月21日 上午8:40:06
*/
@Getter
public class TokenResponse {

    /**
     * jwt token
     */
    private final String token;

    /**
     * 用于客户端混淆md5加密
     */
    private final String randomKey;
    
    public TokenResponse(String token,String randomKey) {
		// TODO Auto-generated constructor stub
    	this.token = token;
    	this.randomKey = randomKey;
	}
}
