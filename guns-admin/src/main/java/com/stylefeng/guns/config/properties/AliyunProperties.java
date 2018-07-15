package com.stylefeng.guns.config.properties;

import com.google.common.collect.Maps;
import com.stylefeng.guns.core.common.enums.IotEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
 * 阿里云配置
 * @author myc
 *
 */
@Component
@ConfigurationProperties(prefix = AliyunProperties.ALIYUNCONF_PREFIX)
public class AliyunProperties {
	public static final String ALIYUNCONF_PREFIX = "aliyun";
		
	Map<String, Object> iot = Maps.newHashMap();

    Map<String, Object> oss = Maps.newHashMap();

    public void setIot(Map<String, Object> iot) {
        this.iot = iot;
    }

    public void setOss(Map<String, Object> oss) {
        this.oss = oss;
    }

    public void setIotLivingAppKey(String iotLivingAppKey) {
        this.iotLivingAppKey = iotLivingAppKey;
    }

    public void setIotLivingAppSecret(String iotLivingAppSecret) {
        this.iotLivingAppSecret = iotLivingAppSecret;
    }

    String iotLivingAppKey;

	String iotLivingAppSecret;
	
	public Map<String, Object> getIot() {
		return iot;
	}

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

    public Map<String, Object> getOss() {
        return oss;
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

    public String getIotLivingAppKey() {
        return iotLivingAppKey;
    }

    public String getIotLivingAppSecret() {
        return iotLivingAppSecret;
    }

}
