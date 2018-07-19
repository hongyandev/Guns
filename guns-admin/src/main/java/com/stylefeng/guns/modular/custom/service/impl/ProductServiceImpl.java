package com.stylefeng.guns.modular.custom.service.impl;

import com.stylefeng.guns.aliyun.iotx.api.client.IoTApiResponse;
import com.stylefeng.guns.aliyun.iotx.api.client.ProductResponse;
import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.common.enums.IotEnum;
import com.stylefeng.guns.core.common.exception.IotApiRepsEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.ApiClientKit;
import com.stylefeng.guns.modular.custom.dao.ProductExtendMapper;
import com.stylefeng.guns.modular.custom.dao.ProductFunattriMapper;
import com.stylefeng.guns.modular.custom.dao.ProductImageMapper;
import com.stylefeng.guns.modular.custom.dao.ProductMapper;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.model.ProductFunattri;
import com.stylefeng.guns.modular.custom.service.IProductService;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductExtendMapper productExtendMapper;
    @Autowired
    ProductFunattriMapper productFunattriMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    AliyunProperties aliyunProperties;

    @Override
    @Transactional
    public void pullProductInfoFromIot(Product product) throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("productKey", product.getProductKey());
        IoTApiResponse request = apiKit.initAliyunIoTApiRequest(IotEnum.fromCode(product.getIotPackage()), params, aliyunProperties.getApiVer(IotEnum.fromCode(product.getIotPackage()), "product"), true);
        String content = apiKit.doIoTApiRequest(aliyunProperties.getApiHost(IotEnum.fromCode(product.getIotPackage())), "/cloud/thing/product/get", true, request);
        ProductResponse response = JSONObject.parseObject(content, ProductResponse.class);
        Product _product = response.getData();
        if (Objects.isNull(_product)) 
        	throw new GunsException(IotApiRepsEnum.PRODUCT_NOT_FOUND);
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
        productImageMapper.deleteById(productKey);
	}
}
