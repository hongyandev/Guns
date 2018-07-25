package com.stylefeng.guns.rest.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
* create by guanqing
* 2018年7月17日 下午5:26:47
*/
@Data
@Configuration
@ConfigurationProperties(prefix = Oauth2Properties.Oauth2_PREFIX)
public class Oauth2Properties {
	
	public static final String Oauth2_PREFIX = "oauth2";
	
	private String appKey;
	
	private String appSecret;
	
	private Long tokenExpiration;
	
	private Long refreshTokenExpiration;
	
	private String tokenKey;
}
