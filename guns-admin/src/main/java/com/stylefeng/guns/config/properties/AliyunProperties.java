package com.stylefeng.guns.config.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
/**
 * 阿里云配置
 * @author myc
 *
 */
@Component
@ConfigurationProperties(prefix = AliyunProperties.ALIYUNCONF_PREFIX)
public class AliyunProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";
	
	String isDev;
	
	Map<String, Object> livingConf = Maps.newHashMap();
	
	public String getLivingApiHost() {
		return (String) livingConf.get("apiHost");
	}
	
	@SuppressWarnings("unchecked")
	public String getLivingApiVer(String key) {
		return (String) ((Map<String,Object>) livingConf.get("apiVer")).get(key);
	}
	
	@SuppressWarnings("unchecked")
	public String getLivingAppKey() {
		return (String) ((Map<String,Object>) livingConf.get("appKey")).get(isDev);
	}
	
	@SuppressWarnings("unchecked")
	public String getLivingAppSecret() {
		return (String) ((Map<String,Object>) livingConf.get("appSecret")).get(isDev);
	}
}
