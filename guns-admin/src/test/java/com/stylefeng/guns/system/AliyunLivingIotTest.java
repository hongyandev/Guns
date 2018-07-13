package com.stylefeng.guns.system;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.stylefeng.guns.aliyun.iotx.api.client.IoTApiRequest;
import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.util.ApiClientKit;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.service.impl.ProductServiceImpl;

public class AliyunLivingIotTest extends BaseJunit {
	
	@Autowired
	ApiClientKit kit;
	@Autowired
	AliyunProperties prop;
	@Autowired
	ProductServiceImpl service;
	
	@Test
	public void cloudToken() {
		String token = kit.getCloudToken(ApiClientKit.IOT_ALIYUN_LIVING);
		System.out.println("cloudToken >>> " + token);
	}
	
	@Test
	public void getProduct() {
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("productKey", "a1SyFi7susU");
			IoTApiRequest request = kit.initAliyunIoTApiRequest(ApiClientKit.IOT_ALIYUN_LIVING, params, prop.getApiVer(ApiClientKit.IOT_ALIYUN_LIVING, "product"), true);
			String str = kit.doIoTApiRequest(prop.getApiHost(ApiClientKit.IOT_ALIYUN_LIVING), "/cloud/thing/product/get", true, request);
			System.out.println(">>> " + str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void pullProduct() {
		try {
			Product product = new Product();
			product.setProductKey("a1SyFi7susU");
			product.setIotPackage(ApiClientKit.IOT_ALIYUN_LIVING);
			Product _product = service.pullProductInfoFromIot(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
