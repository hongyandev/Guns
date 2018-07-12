package com.stylefeng.guns.modular.product.service.impl;

import com.stylefeng.guns.modular.system.model.Product;
import com.stylefeng.guns.modular.system.dao.ProductMapper;
import com.stylefeng.guns.modular.product.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
	
	/**
	 * 从云端拉取产品信息
	 * @author myc
	 * @since 2018-07-12
	 */
	@Override
	public Product pullProductInfoFromCloud(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
