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
		
	Map<String, Object> livingConf = Maps.newHashMap();
	
	public Map<String, Object> getLivingConf() {
		return livingConf;
	}

	public void setLivingConf(Map<String, Object> livingConf) {
		this.livingConf = livingConf;
	}

	public String getLivingApiHost() {
		return String.valueOf(livingConf.get("apiHost"));
	}
	
	@SuppressWarnings("unchecked")
	public String getLivingApiVer(String key) {
		return String.valueOf(((Map<String,Object>) livingConf.get("apiVer")).get(key));
	}
	
	public String getLivingAppKey() {
		return String.valueOf(livingConf.get("appKey"));
	}
	
	public String getLivingAppSecret() {
		return String.valueOf(livingConf.get("appSecret"));
	}
}
