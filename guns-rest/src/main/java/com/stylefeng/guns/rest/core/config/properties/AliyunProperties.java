package com.stylefeng.guns.rest.core.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
* create by guanqing
* 2018年7月27日 上午11:29:37
*/
@Data
@Configuration
@ConfigurationProperties(prefix = AliyunProperties.ALIYUN_PREFIX)
public class AliyunProperties {
	
	protected static final String ALIYUN_PREFIX = "aliyun";
	
	private Msg msg;
	
	@Data
	public static class Msg {
		
		private String appKey;
		
		private String appSecret;
	}
	
}
