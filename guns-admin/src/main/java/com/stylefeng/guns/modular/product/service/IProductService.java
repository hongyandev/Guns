package com.stylefeng.guns.modular.product.service;

import com.stylefeng.guns.modular.system.model.Product;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-12
 */
public interface IProductService extends IService<Product> {
	public Product pullProductInfoFromCloud(Product product);
}
