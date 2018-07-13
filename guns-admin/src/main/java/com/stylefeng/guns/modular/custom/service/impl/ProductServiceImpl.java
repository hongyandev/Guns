package com.stylefeng.guns.modular.custom.service.impl;

import com.stylefeng.guns.modular.custom.dao.ProductMapper;
import com.stylefeng.guns.modular.custom.model.Product;
import com.stylefeng.guns.modular.custom.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

}
