package com.stylefeng.guns.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.bean.Subscription;

import lombok.Data;
@Data
@Component
@ConfigurationProperties(prefix = AliOnsProperties.ALIONSCONF_PREFIX)
public class AliOnsProperties {
	public static final String ALIONSCONF_PREFIX = "aliyun.ons";
	String producerId;
	String consumerId;
	String accessKey;
	String secretKey;
	String consumeThreadNums;
	String onsAddr;
	List<Subscription> subscription;
}
