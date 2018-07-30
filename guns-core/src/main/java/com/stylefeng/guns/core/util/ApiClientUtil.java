package com.stylefeng.guns.core.util;
/**
* create by guanqing
* 2018年7月30日 下午1:11:24
*/

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.stylefeng.guns.core.aliyun.IoTApiRequest;
import com.stylefeng.guns.core.aliyun.IoTApiResponse;
import com.stylefeng.guns.core.aliyun.SyncApiClient;
import com.stylefeng.guns.core.config.properties.AliyunProperties;
import com.stylefeng.guns.core.enums.IotApiRepsType;
import com.stylefeng.guns.core.enums.IotType;
import com.stylefeng.guns.core.exception.GunsException;

@Component
public class ApiClientUtil {

	@Autowired
	AliyunProperties aliyunProperties;
	@Autowired
	RedisUtil redisUtil;
	
	private SyncApiClient livingClient;
	
	private SyncApiClient getSyncApiClient(IotType iot) {
		switch (iot) {
		case LIVING:{
			if(Objects.nonNull(livingClient))
				return livingClient;
			livingClient = SyncApiClient.newBuilder().appKey(aliyunProperties.getIot().getLiving().getAppKey())
					.appSecret(aliyunProperties.getIot().getLiving().getAppSecret()).build();
			return livingClient;
		}
		default:
			break;
		}
		return null;
	}
	
	private String doIoTApiRequest(IotType iot,String host,String path,boolean isHttps,IoTApiRequest request) {
		try {
			ApiResponse response = getSyncApiClient(iot).postBody(host, path, request, isHttps);
			if (response.getStatusCode() != 200)
				throw new GunsException(IotApiRepsType.getIotApiRepsType(response.getStatusCode()));
			return new String(response.getBody(), "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GunsException(IotApiRepsType.SERVICE_ERROR);
		}
	}
	
	/**
	 * 阿里 living 平台请求
	 * @param path
	 * @param request
	 * @return
	 */
	public String doLivingIotApiRequest(String path,IoTApiRequest request) {
		IotType iot = IotType.LIVING;
		String host = aliyunProperties.getIot().getLiving().getApiHost();
		return doIoTApiRequest(iot, host, path, true, request);
	}
	
	private String livingToken() {
		Object livingToken = redisUtil.get(aliyunProperties.getIot().getLiving().getAppToken());
		if (Objects.nonNull(livingToken)) {
			return (String) livingToken;
		}else {
			Map<String, Object> params = Maps.newHashMap();
			params.put("grantType", "project");
			params.put("res", "a124YO0oU3Qm4SGF");
			IoTApiRequest request = initLivingProjectApiRequest(params, false);
			String content = doIoTApiRequest(IotType.LIVING, aliyunProperties.getIot().getLiving().getApiHost(), "/cloud/token", true, request);
			IoTApiResponse<Map<String, Object>> response = JSONObject.parseObject(content, IoTApiResponse.class);
			if (response.getCode() != 200)
				throw new GunsException(IotApiRepsType.getIotApiRepsType(response.getCode()));
			livingToken = response.getData().get("cloudToken");
			redisUtil.set(aliyunProperties.getIot().getLiving().getAppToken(), livingToken, aliyunProperties.getIot().getLiving().getTokenExpired());
			return (String) livingToken;
		}
	}
	
	private String homeLinkToken() {
		return "";
	}
	
	
	private String getIotToken(IotType iotType) {
		String iotToken = "";
		switch (iotType) {
		case LIVING:
			iotToken = livingToken();
			break;
		case HOMELINK:
			iotToken = homeLinkToken();
		default:
			break;
		}
		return iotToken;
	}
	
	private IoTApiRequest initIotApiRequest(IotType iotType,Map<String, Object> params,String apiVer,Boolean hasToken) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(apiVer);
		if (hasToken)
			request.setIoTToken(getIotToken(iotType));
		params.forEach((key, value) -> {
			request.putParam(key, value);
		});
		return request;
	}
	
	/**
	 * 初始化 living平台 项目的 请求
	 * @param params 请求参数
	 * @param hasToken 是否鉴权
	 * @return
	 */
	public IoTApiRequest initLivingProjectApiRequest(Map<String, Object> params,Boolean hasToken) {
		IotType iotType = IotType.LIVING;
		String apiVer = aliyunProperties.getIot().getLiving().getApiVer().getProject();
		return initIotApiRequest(iotType, params, apiVer, hasToken);
	}
	
	/**
	 * 初始化 living平台 产品的 请求
	 * @param params 请求参数
	 * @param hasToken 是否鉴权
	 * @return
	 */
	public IoTApiRequest initLivingProductApiRequest(Map<String, Object> params,Boolean hasToken) {
		IotType iotType = IotType.LIVING;
		String apiVer = aliyunProperties.getIot().getLiving().getApiVer().getProduct();
		return initIotApiRequest(iotType, params, apiVer, hasToken);
	}
	
	/**
	 * 初始化 living平台 物的 请求
	 * @param params 请求参数
	 * @param hasToken 是否鉴权
	 * @return
	 */
	public IoTApiRequest initLivingPropertiesApiReqeust(Map<String, Object> params,Boolean hasToken) {
		IotType iotType = IotType.LIVING;
		String apiVer = aliyunProperties.getIot().getLiving().getApiVer().getProperties();
		return initIotApiRequest(iotType, params, apiVer, hasToken);
	}
}
