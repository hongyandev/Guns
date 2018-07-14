package com.stylefeng.guns.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;
import com.stylefeng.guns.aliyun.iotx.api.client.IoTApiRequest;
import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.util.ApiClientKit;
import com.stylefeng.guns.modular.custom.dao.ProductExtendMapper;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.service.impl.ProductServiceImpl;

public class AliyunLivingIotTest extends BaseJunit {
	
	@Autowired
	ApiClientKit kit;
	@Autowired
	AliyunProperties prop;
	@Autowired
	ProductServiceImpl service;
	@Autowired
	ProductExtendMapper mapper;
	
	@Test
	public void cloudToken() {
		String token = kit.getCloudToken(ApiClientKit.IOT_ALIYUN_LIVING);
	}
	
	@Test
	public void getProduct() {
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("productKey", "a1SyFi7susU");
			IoTApiRequest request = kit.initAliyunIoTApiRequest(ApiClientKit.IOT_ALIYUN_LIVING, params, prop.getApiVer(ApiClientKit.IOT_ALIYUN_LIVING, "product"), true);
			String str = kit.doIoTApiRequest(prop.getApiHost(ApiClientKit.IOT_ALIYUN_LIVING), "/cloud/thing/product/get", true, request);
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
			service.pullProductInfoFromIot(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void T() {
		/*Map m = new HashMap<>();
		m.put("productKey", "a1H8jN4MJTx");
		List<ProductExtend> list = this.mapper.selectByMap(m);
		list.forEach(l->{
			System.out.println(l);
		});*/
		EntityWrapper<ProductExtend> entityWrapper = new EntityWrapper<>();
		Wrapper<ProductExtend> wrapper = entityWrapper.eq("productKey", "a1H8jN4MJTx");
		List<ProductExtend> list = this.mapper.selectList(wrapper);
		list.forEach(l->{
			System.out.println(l);
		});
	}
	
}
