package com.stylefeng.guns.core.util;

import com.stylefeng.guns.core.cache.CacheKit;
import com.stylefeng.guns.core.cache.ILoader;


public class ApiClientKit {
	/**
	 * 阿里云智能生活
	 */
	public final static int IOT_ALIYUN_LIVING = 1;
	/**
	 * 阿里云智能人居
	 */
	public final static int IOT_ALIYUN_HOMELINK = 2;
	
	
	
	String getCloudToken(Integer iot) {
		String token = null;
		switch (iot) {
		case IOT_ALIYUN_LIVING:
			token = CacheKit.get("TOKEN", "IOT_ALIYUN_LIVING_TOKEN", new ILoader() {
				public Object load() {
					
					return "";
				}
			});
			break;
		case IOT_ALIYUN_HOMELINK:
			token = CacheKit.get("TOKEN", "IOT_ALIYUN_HOMELINK_TOKEN", new ILoader() {
				public Object load() {
					return "";
				}
			});
			break;
		default:
			break;
		}
		return token;
	}
	
}
