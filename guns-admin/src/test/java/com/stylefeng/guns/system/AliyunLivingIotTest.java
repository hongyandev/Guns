package com.stylefeng.guns.system;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.common.enums.IotEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;
import com.stylefeng.guns.aliyun.IoTApiRequest;
import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.config.properties.AliyunProperties;
import com.stylefeng.guns.core.util.ApiClientKit;
import com.stylefeng.guns.modular.custom.dao.ProductExtendMapper;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.model.ProductExtend;
import com.stylefeng.guns.modular.custom.service.impl.ProductServiceImpl;

public class AliyunLivingIotTest extends BaseJunit {

    @Autowired
    ApiClientKit kit;
    @Autowired
    AliyunProperties prop;
    @Autowired
    ProductServiceImpl service;
    @Autowired
    ProductExtendMapper mapper;

    @Test
    public void cloudToken() {
        String token = kit.getCloudToken(IotEnum.LIVING);
    }

    @Test
    public void getProduct() {
        try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("productKey", "a1SyFi7susU");
            IoTApiRequest request = kit.initAliyunIoTApiRequest(IotEnum.LIVING, params, prop.getApiVer(IotEnum.LIVING, "product"), true);
            String str = kit.doIoTApiRequest(prop.getApiHost(IotEnum.LIVING), "/cloud/thing/product/get", true, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void pullProduct() {
        try {
            Product product = new Product();
            product.setProductKey("a1SyFi7susU");
            product.setIotPackage(IotEnum.LIVING.getCode());
            service.pullProductInfoFromIot(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T() {
		/*Map m = new HashMap<>();
		m.put("productKey", "a1H8jN4MJTx");
		List<ProductExtend> list = this.mapper.selectByMap(m);
		list.forEach(l->{
			System.out.println(l);
		});*/
        EntityWrapper<ProductExtend> entityWrapper = new EntityWrapper<>();
        Wrapper<ProductExtend> wrapper = entityWrapper.eq("productKey", "a1H8jN4MJTx");
        List<ProductExtend> list = this.mapper.selectList(wrapper);
        list.forEach(l -> {
            System.out.println(l);
        });
    }

}
