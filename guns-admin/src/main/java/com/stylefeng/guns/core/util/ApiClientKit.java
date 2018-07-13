package com.stylefeng.guns.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.stylefeng.guns.aliyun.iotx.api.client.IoTApiRequest;
import com.stylefeng.guns.aliyun.iotx.api.client.SyncApiClient;
import com.stylefeng.guns.aliyun.iotx.api.client.ApiBaseResponse;
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
	/**
	 * 阿里云智能生活
	 */
	public final static int IOT_ALIYUN_LIVING = 1;
	/**
	 * 阿里云智能人居
	 */
	public final static int IOT_ALIYUN_HOMELINK = 2;
	
	@Autowired
	AliyunProperties aliyunProperties;
	
	SyncApiClient livingSyncClient;
		
	public AliyunProperties getAliyunProperties() {
		return aliyunProperties;
	}

	/**
	 * 获取Living客户端
	 * @author myc
	 * @return
	 */
	private SyncApiClient getLivingSyncClient() {
		return livingSyncClient != null ? livingSyncClient : SyncApiClient.newBuilder()
	            .appKey(aliyunProperties.getAppKey(ApiClientKit.IOT_ALIYUN_LIVING))
	            .appSecret(aliyunProperties.getAppSecret(ApiClientKit.IOT_ALIYUN_LIVING))
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
	
	public String doIoTApiRequest(String host, String path, boolean isHttps, IoTApiRequest request) throws Exception {
		ApiResponse response = getLivingSyncClient().postBody(host, path, request, true);
		if (response.getStatusCode() == 200) {
			return new String(response.getBody(), "utf-8");
		} else {
			throw new Exception("response code = " + response.getStatusCode());
		}
	}

	public String getCloudToken(Integer iot) {
		String token = null;
		switch (iot) {
		case IOT_ALIYUN_LIVING:
			token = CacheKit.get("TOKEN", "IOT_ALIYUN_LIVING_TOKEN", new ILoader() {
				public Object load() {
					try {
						Map<String, Object> params = Maps.newHashMap();
						params.put("grantType", "project");
						params.put("res", "a124YO0oU3Qm4SGF");
						IoTApiRequest request = initAliyunIoTApiRequest(iot, params, aliyunProperties.getApiVer(ApiClientKit.IOT_ALIYUN_LIVING, "project"), false);
						String content = doIoTApiRequest(aliyunProperties.getApiHost(ApiClientKit.IOT_ALIYUN_LIVING), "/cloud/token", true, request);
						ApiBaseResponse response = JSONObject.parseObject(content, ApiBaseResponse.class);
						if (response.getCode() == 200) {
							return response.getData().get("cloudToken");
						} else {
							System.out.println(content);
							throw new Exception(content);
						}
					} catch (Exception e) {
						e.printStackTrace();
						return "";
					}
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
			token = "";
			break;
		}
		return token;
	}
	
	/**
	 * 组装请求Body
	 * @author myc
	 * @param iot IoT平台代码
	 * @param params Body参数
	 * @param apiVer 资源版本号
	 * @param hasToken 是否鉴权
	 * @return
	 */
	public IoTApiRequest initAliyunIoTApiRequest(Integer iot, Map<String, Object> params, String apiVer, Boolean hasToken) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(apiVer);
		if (hasToken) {
			request.setIoTToken(getCloudToken(iot));
		}
		params.forEach((key, value) -> {
			request.putParam(key, value);
		});
		return request;
	}
	
}
