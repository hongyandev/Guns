package com.stylefeng.guns.aliyun.iotx.api.client;

import com.stylefeng.guns.modular.custom.model.Product;

/**
 * 产品属性
 */
public class ProductResponse extends BaseResponse<ProductResponse> {
	private static final long serialVersionUID = 1L;
	private Product data;

	public Product getData() {
		return data;
	}

	public void setData(Product data) {
		this.data = data;
	}
}
