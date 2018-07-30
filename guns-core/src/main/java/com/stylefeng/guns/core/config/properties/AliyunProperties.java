package com.stylefeng.guns.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
  * 阿里云相关配置
  * create by guanqing
  * 2018年7月20日 上午10:17:47
  */
@Data
@Component
@ConfigurationProperties(prefix = AliyunProperties.ALIYUNCONF_PREFIX)
public class AliyunProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";

	private Oss oss;
	private Iot iot;
	
	@Data
	public static class Oss {
		// 资源空间名
		private String bucket;
		// 资源自定义域名
		private String domain;
		// accessKeyId
		private String accessKeyId;
		// accessKeySecret
		private String accessKeySecret;
	}
	
	@Data
	public static class Iot {
		
		// living 平台
		private Living living;
		
		@Data
		public static class Living{
			// api 域名
			private String apiHost;
			// appKey
			private String appKey;
			// appSecret
			private String appSecret;
			// appToken
			private String appToken;
			// token 有效期
			private int tokenExpired;
			// apiVer 对象
			private ApiVer apiVer;
			
			@Data
			public static class ApiVer {
				// 项目
				private String project;
				// 产品
				private String product;
				// 物
				private String properties;
			}
			
		}
		
	} 
}
