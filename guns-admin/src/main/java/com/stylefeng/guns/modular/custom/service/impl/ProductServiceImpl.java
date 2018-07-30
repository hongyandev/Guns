package com.stylefeng.guns.modular.custom.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.stylefeng.guns.core.aliyun.IoTApiRequest;
import com.stylefeng.guns.core.aliyun.IoTApiResponse;
import com.stylefeng.guns.core.config.properties.AliyunProperties;
import com.stylefeng.guns.core.domain.FilePath;
import com.stylefeng.guns.core.enums.IotApiRepsType;
import com.stylefeng.guns.core.enums.IotType;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.ApiClientUtil;
import com.stylefeng.guns.core.util.OssUtil;
import com.stylefeng.guns.modular.custom.dao.ProductExtendMapper;
import com.stylefeng.guns.modular.custom.dao.ProductFileMapper;
import com.stylefeng.guns.modular.custom.dao.ProductFunattriMapper;
import com.stylefeng.guns.modular.custom.dao.ProductImageMapper;
import com.stylefeng.guns.modular.custom.dao.ProductMapper;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.model.ProductFile;
import com.stylefeng.guns.modular.custom.model.ProductFunattri;
import com.stylefeng.guns.modular.custom.model.ProductImage;
import com.stylefeng.guns.modular.custom.service.IProductService;

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
    ApiClientUtil apiUtil;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductExtendMapper productExtendMapper;
    @Autowired
    ProductFunattriMapper productFunattriMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    ProductFileMapper productFileMapper;
    @Autowired
    AliyunProperties aliyunProperties;
    @Autowired
    OssUtil ossUtil;

    @Override
    @Transactional
    public void pullProductInfoFromIot(Product product) throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("productKey", product.getProductKey());
        IoTApiRequest request = apiUtil.initLivingProductApiRequest(params, true);
        String content = apiUtil.doLivingIotApiRequest("/cloud/thing/product/get", request);
        IoTApiResponse<Product> response = JSONObject.parseObject(content, IoTApiResponse.class);
        Product _product = response.getData();
        if (Objects.isNull(_product)) 
        	throw new GunsException(IotApiRepsType.PRODUCT_NOT_FOUND);
        _product.setIotPackage(product.getIotPackage());
        _product.insertOrUpdate();
        
        EntityWrapper<ProductExtend> entityWrapper = new EntityWrapper<>();
        Wrapper<ProductExtend> wrapper = entityWrapper.eq("productKey", product.getProductKey());
        productExtendMapper.delete(wrapper);
        
        List<ProductExtend> exts = _product.getExtendProperties();
        exts.forEach(ext -> {
            ext.insert();
        });
    }
    
    public void getProperties() {
    	Map<String, Object> params = Maps.newHashMap();
    	params.put("iotId", "g6XQoz4xQZL4UXQwSDpt00105a7d00");
    	IoTApiRequest request = apiUtil.initLivingPropertiesApiReqeust(params, false);
    	String content = apiUtil.doLivingIotApiRequest("/cloud/thing/properties/get", request);
    	System.out.println("content >> "+ content);
    }
    
    public void setProperties() {
    	Map<String, Object> params = Maps.newHashMap();
    	params.put("iotId", "g6XQoz4xQZL4UXQwSDpt00105a7d00");
    	Map<String, Object> attr = Maps.newHashMap();
    	attr.put("CurtainPosition", 18);
    	params.put("items", attr);
    	IoTApiRequest request = apiUtil.initLivingPropertiesApiReqeust(params, true);
    	String content = apiUtil.doLivingIotApiRequest("/cloud/thing/properties/set", request);
    	System.out.println("content >> "+ content);
    }
    
    @Override
    public List<ProductExtend> selectByProductKey(String productKey) {
        // TODO Auto-generated method stub
        EntityWrapper<ProductExtend> entityWrapper = new EntityWrapper<>();
        Wrapper<ProductExtend> wrapper = entityWrapper.eq("productKey", productKey);
        List<ProductExtend> list = productExtendMapper.selectList(wrapper);
        return list;
    }

	@Override
	public ProductFunattri selectFunattriByProductKey(String productKey) {
		// TODO Auto-generated method stub
		return productFunattriMapper.selectById(productKey);
	}

	@Override
	public void updateFunattriByProductKey(ProductFunattri funattri) {
		// TODO Auto-generated method stub
		funattri.insertOrUpdate();
	}

	@Override
	@Transactional
	public void deleteProductAndRelated(String productKey) {
		// TODO Auto-generated method stub
		productMapper.deleteById(productKey);
        EntityWrapper<ProductExtend> entityWrapper = new EntityWrapper<>();
        Wrapper<ProductExtend> wrapper = entityWrapper.eq("productKey", productKey);
        productExtendMapper.delete(wrapper);
        productFunattriMapper.deleteById(productKey);
        ProductImage image = productImageMapper.selectById(productKey);
        productImageMapper.deleteById(productKey);
        if(Objects.nonNull(image) && StringUtils.isNotBlank(image.getFileKey())) {
        	FilePath path = new FilePath();
        	BeanUtils.copyProperties(image, path);
        	ossUtil.deleteObjects(Arrays.asList(path));
        }
	}

	@Override
	public ProductImage selectImageByProductKey(String productKey) {
		// TODO Auto-generated method stub
		return productImageMapper.selectById(productKey);
	}

	@Override
	public void saveProductImage(String productKey, FilePath path) {
		// TODO Auto-generated method stub
		ProductImage image = productImageMapper.selectById(productKey);
		if (Objects.nonNull(image)) {
			FilePath filePath = new FilePath();
			BeanUtils.copyProperties(image, filePath);
			ossUtil.deleteObjects(Arrays.asList(filePath));
			BeanUtils.copyProperties(path, image);
			image.setLastModify(new Date());
			image.updateById();
		}else {
			image = new ProductImage();
			BeanUtils.copyProperties(path, image);
			image.setProductKey(productKey);
			image.setLastModify(new Date());
			image.insert();
		}
	}

	@Override
	public void deleteProductImage(String productKey) {
		// TODO Auto-generated method stub
		ProductImage image = productImageMapper.selectById(productKey);
		if(Objects.nonNull(image)) {
			FilePath filePath = new FilePath();
			BeanUtils.copyProperties(image, filePath);
			productImageMapper.deleteById(productKey);
			ossUtil.deleteObjects(Arrays.asList(filePath));
		}
	}

	@Override
	public ProductFile selectFileByProductKey(String productKey) {
		// TODO Auto-generated method stub
		return productFileMapper.selectById(productKey);
	}

	@Override
	public void saveProductFile(String productKey, FilePath path) {
		// TODO Auto-generated method stub
		ProductFile file = productFileMapper.selectById(productKey);
		if (Objects.nonNull(file)) {
			FilePath filePath = new FilePath();
			BeanUtils.copyProperties(file, filePath);
			ossUtil.deleteObjects(Arrays.asList(filePath));
			BeanUtils.copyProperties(path, file);
			file.setLastModify(new Date());
			file.updateById();
		}else {
			file = new ProductFile();
			BeanUtils.copyProperties(path, file);
			file.setProductKey(productKey);
			file.setLastModify(new Date());
			file.insert();
		}
	}

	@Override
	public void deleteProductFile(String productKey) {
		// TODO Auto-generated method stub
		ProductFile file = productFileMapper.selectById(productKey);
		if (Objects.nonNull(file)) {
			FilePath filePath = new FilePath();
			BeanUtils.copyProperties(file, filePath);
			productFileMapper.deleteById(productKey);
			ossUtil.deleteObjects(Arrays.asList(filePath));
		}
	}
}
