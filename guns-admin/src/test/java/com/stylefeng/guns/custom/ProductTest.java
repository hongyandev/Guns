package com.stylefeng.guns.custom;

import java.util.Date;

import org.junit.Test;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.custom.model.ProductImage;

/**
* create by guanqing
* 2018年7月19日 下午9:02:53
*/
public class ProductTest extends BaseJunit {
	
	@Test
	public void T() {
		ProductImage image = new ProductImage();
		image.setProductKey("abc");
		image.setLastModify(new Date());
		image.insertOrUpdate();
	}

}
