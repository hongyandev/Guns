package com.stylefeng.guns.config.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.stylefeng.guns.core.util.ApiClientKit;
/**
 * 阿里云配置
 * @author myc
 *
 */
@Component
@ConfigurationProperties(prefix = AliyunProperties.ALIYUNCONF_PREFIX)
public class AliyunProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";
		
	Map<String, Object> iotConf = Maps.newHashMap();
	
	public Map<String, Object> getIotConf() {
		return iotConf;
	}

	public void setIotConf(Map<String, Object> iotConf) {
		this.iotConf = iotConf;
	}

	@SuppressWarnings("unchecked")
	public String getApiHost(Integer iot) {
		return String.valueOf(((Map<String,Object>) iotConf.get(getIotName(iot))).get("apiHost"));
	}
	
	@SuppressWarnings("unchecked")
	public String getApiVer(Integer iot, String resKey) {
		return String.valueOf(((Map<String,Object>) ((Map<String,Object>) iotConf.get(getIotName(iot))).get("apiVer")).get(resKey));
	}
	
	private String getIotName(Integer iot) {
		String iotName = null;
		switch (iot) {
		case ApiClientKit.IOT_ALIYUN_LIVING:
			iotName = "living";
			break;
		default:
			iotName = "";
			break;
		}
		return iotName;
	}

	@SuppressWarnings("unchecked")
	public String getAppKey(Integer iot) {
		return String.valueOf(((Map<String,Object>) iotConf.get(getIotName(iot))).get("appKey"));
	}
	
	@SuppressWarnings("unchecked")
	public String getAppSecret(Integer iot) {
		return String.valueOf(((Map<String, Object>) iotConf.get(getIotName(iot))).get("appSecret"));
	}
}
