package com.stylefeng.guns.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
  * 阿里云相关配置
  * create by guanqing
  * 2018年7月20日 上午10:17:47
  */
@Data
@Component
@ConfigurationProperties(prefix = AliyunOssProperties.ALIYUNCONF_PREFIX)
public class AliyunOssProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";

    Map<String, Object> oss;


    public String getOssBucket() {
	    return (String) oss.get("bucket");
    }

    public String getOssDomain() {
	    return (String) oss.get("domain");
    }

    public String getOssAccessKeyId() {
	    return (String) oss.get("accessKeyId");
    }

    public String getOssAccessKeySecret() {
	    return (String) oss.get("accessKeySecret");
    }

}
