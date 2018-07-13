package com.stylefeng.guns.modular.custom.service.impl;

import com.stylefeng.guns.aliyun.iotx.api.client.IoTApiRequest;
import com.stylefeng.guns.aliyun.iotx.api.client.ProductResponse;
import com.stylefeng.guns.aliyun.iotx.api.client.ApiBaseResponse;
import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.util.ApiClientKit;
import com.stylefeng.guns.modular.custom.dao.ProductMapper;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.service.IProductService;

import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

	@Autowired
	ApiClientKit apiKit;
	
	@Override
	public Product pullProductInfoFromIot(Product product) throws Exception {
		Map<String, Object> params = Maps.newHashMap();
		params.put("productKey", product.getProductKey());
		AliyunProperties prop = apiKit.getAliyunProperties();
		IoTApiRequest request = apiKit.initAliyunIoTApiRequest(product.getIotPackage(), params, prop.getApiVer(product.getIotPackage(), "product"), true);
		String content = apiKit.doIoTApiRequest(prop.getApiHost(product.getIotPackage()), "/cloud/thing/product/get", true, request);
		ProductResponse response = null;
		try {
			response = JSONObject.parseObject(content, ProductResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(content);
		}
		Product _product = response.getData();
		_product.setIotPackage(product.getIotPackage());
		System.out.println(_product.toString());
		_product.insertOrUpdate();
		List<ProductExtend> exts = _product.getExtendProperties();
		for (ProductExtend ext : exts) {
			System.out.println(ext.toString());
			ext.insertOrUpdate();
		}
		return _product;
	}

}
