package com.stylefeng.guns.modular.custom.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.model.ProductFunattri;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
public interface IProductService extends IService<Product> {
	public void pullProductInfoFromIot(Product product) throws Exception;
	
	public List<ProductExtend> selectByProductKey(String productKey);
	
	public ProductFunattri selectFunattriByProductKey(String productKey);
	
	public void updateFunattriByProductKey(ProductFunattri funattri);
}
