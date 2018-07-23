package com.stylefeng.guns.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.stylefeng.guns.core.common.enums.IotEnum;
import com.stylefeng.guns.core.common.exception.IotApiRepsEnum;
import com.stylefeng.guns.core.exception.GunsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.stylefeng.guns.aliyun.IoTApiRequest;
import com.stylefeng.guns.aliyun.IoTApiResponse;
import com.stylefeng.guns.aliyun.SyncApiClient;
import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.cache.CacheKit;
import com.stylefeng.guns.core.cache.ILoader;

/**
 * Api客户端组件
 * @author myc
 *
 */
@Service
public class ApiClientKit {
	
	@Autowired
	AliyunProperties aliyunProperties;
	
	SyncApiClient livingSyncClient;
		
	/**
	 * 获取Living客户端
	 * @author myc
	 * @return
	 */
	private SyncApiClient getLivingSyncClient() {
		return livingSyncClient != null ? livingSyncClient : SyncApiClient.newBuilder()
	            .appKey(aliyunProperties.getAppKey(IotEnum.LIVING))
	            .appSecret(aliyunProperties.getAppSecret(IotEnum.LIVING))
	            .build();
	}
	
	/**
	 * 执行Api请求
	 * @author myc
	 * @param host 资源地址
	 * @param path 资源路径
	 * @param isHttps SSL
	 * @param request 组装好的请求Body
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	
	public String doIoTApiRequest(String host, String path, boolean isHttps, IoTApiRequest request) throws GunsException {
		try {
			ApiResponse response = getLivingSyncClient().postBody(host, path, request, isHttps);
			if (response.getStatusCode() != 200)
				throw new GunsException(IotApiRepsEnum.fromCode(response.getStatusCode()));
			return new String(response.getBody(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new GunsException(IotApiRepsEnum.SERVICE_ERROR);
		}
	}

	public String getCloudToken(IotEnum iotEnum) {
		String token = null;
		switch (iotEnum) {
			case LIVING:
			token = CacheKit.get("TOKEN", "IOT_ALIYUN_LIVING_TOKEN", new ILoader() {
				public Object load() {
					Map<String, Object> params = Maps.newHashMap();
					params.put("grantType", "project");
					params.put("res", "a124YO0oU3Qm4SGF");
					IoTApiRequest request = initAliyunIoTApiRequest(IotEnum.LIVING, params, aliyunProperties.getApiVer(IotEnum.LIVING, "project"), false);
					String content = doIoTApiRequest(aliyunProperties.getApiHost(IotEnum.LIVING), "/cloud/token", true, request);
					IoTApiResponse<Map<String, Object>> response = JSONObject.parseObject(content, IoTApiResponse.class);
					if (response.getCode() != 200)
						throw new GunsException(IotApiRepsEnum.fromCode(response.getCode()));
					return response.getData().get("cloudToken");
				}
			});
			break;
        case HOMELINK:
			token = CacheKit.get("TOKEN", "IOT_ALIYUN_HOMELINK_TOKEN", new ILoader() {
				public Object load() {
					return "";
				}
			});
			break;
		default:
			token = "";
			break;
		}
		return token;
	}
	
	/**
	 * 组装请求Body
	 * @author myc
	 * @param iotEnum IoT平台代码
	 * @param params Body参数
	 * @param apiVer 资源版本号
	 * @param hasToken 是否鉴权
	 * @return
	 */
	public IoTApiRequest initAliyunIoTApiRequest(IotEnum iotEnum, Map<String, Object> params, String apiVer, Boolean hasToken) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(apiVer);
		if (hasToken) {
			request.setIoTToken(getCloudToken(iotEnum));
		}
		params.forEach((key, value) -> {
			request.putParam(key, value);
		});
		return request;
	}
	
}
