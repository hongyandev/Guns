package com.stylefeng.guns.system;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.util.ApiClientKit;

public class AliyunLivingIotTest extends BaseJunit {
	
	@Autowired
	ApiClientKit kit;
	
	@Test
	public void cloudToken() {
		String token = kit.getCloudToken(ApiClientKit.IOT_ALIYUN_LIVING);
		System.out.println("cloudToken >>> " + token);
	}
	
	public void getProduct() {
		
	}
	
}
