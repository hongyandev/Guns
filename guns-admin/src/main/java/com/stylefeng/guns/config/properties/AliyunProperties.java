package com.stylefeng.guns.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = AliyunProperties.ALIYUNCONF_PREFIX)
public class AliyunProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";
	
	
}
