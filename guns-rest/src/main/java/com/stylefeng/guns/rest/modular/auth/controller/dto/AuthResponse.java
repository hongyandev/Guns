package com.stylefeng.guns.rest.modular.auth.controller.dto;

import java.io.Serializable;

/**
 * 认证的响应结果
 *
 * @author guanqing
 * @Date 2018/7/16 21:58
 */
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    /**
     * jwt token
     */
    private final String token;

    /**
     * 用于客户端混淆md5加密
     */
    private final String randomKey;
    
    /**
     * 颁发给阿里的authCode
     */
    private final String authCode;
    
    /**
     * 用户唯一id
     */
    private final String userId;

	public AuthResponse(String token, String randomKey, String authCode,String userId) {
        this.token = token;
        this.randomKey = randomKey;
        this.authCode = authCode;
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public String getRandomKey() {
        return randomKey;
    }
    
    public String getAuthCode() {
		return authCode;
	}
    
	public String getUserId() {
		return userId;
	}
}
