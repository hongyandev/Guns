package com.stylefeng.guns.config.properties;

import com.stylefeng.guns.core.common.enums.IotEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
 * 阿里云配置
 * @author myc
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = AliyunProperties.ALIYUNCONF_PREFIX)
public class AliyunProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";
		
	Map<String, Object> iot;

    Map<String, Object> oss;

    String iotLivingAppKey;

	String iotLivingAppSecret;

	@SuppressWarnings("unchecked")
	public String getApiHost(IotEnum iotEnum) {
		return String.valueOf(((Map<String,Object>) this.iot.get(iotEnum.getName())).get("apiHost"));
	}
	
	@SuppressWarnings("unchecked")
	public String getApiVer(IotEnum iotEnum, String resKey) {
		return String.valueOf(((Map<String,Object>) ((Map<String,Object>) this.iot.get(iotEnum.getName())).get("apiVer")).get(resKey));
	}

	public String getAppKey(IotEnum iotEnum) {
	    String appKey = null;
	    switch (iotEnum) {
			case LIVING:
                appKey = getIotLivingAppKey();
                break;
            default:
                appKey = "";
        }
        return appKey;
	}
	
	public String getAppSecret(IotEnum iotEnum) {
	    String appSecret;
	    switch (iotEnum) {
            case LIVING:
                appSecret = getIotLivingAppSecret();
                break;
            default:
                appSecret = "";
        }
        return appSecret;
	}

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
