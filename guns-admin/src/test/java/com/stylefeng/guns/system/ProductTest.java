package com.stylefeng.guns.system;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.custom.dao.ProductFunattriMapper;
import com.stylefeng.guns.modular.custom.dao.ProductMapper;

/**
* create by guanqing
* 2018年7月16日 下午5:31:36
*/
public class ProductTest extends BaseJunit {

	@Autowired
	private ProductFunattriMapper funattriMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void T() {
		/*Map map = new HashMap<>();
		map.put("productKey", "a1H8jN4MJTx");
		System.out.println(funattriMapper.selectByMap(map));*/
		System.out.println(funattriMapper.selectById("a1H8jN4MJTx"));
//		System.out.println(productMapper.selectById("a1H8jN4MJTx"));
	}
}
