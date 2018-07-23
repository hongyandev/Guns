package com.stylefeng.guns.modular.custom.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.domain.FilePath;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.model.ProductFile;
import com.stylefeng.guns.modular.custom.model.ProductFunattri;
import com.stylefeng.guns.modular.custom.model.ProductImage;

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
	
	public void deleteProductAndRelated(String productKey);
	
	public ProductImage selectImageByProductKey(String productKey);
	
	public void saveProductImage(String productKey,FilePath path);
	
	public void deleteProductImage(String productKey);

	public ProductFile selectFileByProductKey(String productKey);
}
