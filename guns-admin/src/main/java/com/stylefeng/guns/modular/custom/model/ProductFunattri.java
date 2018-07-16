package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产品功能属性
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-16
 */
@TableName("app_product_funattri")
public class ProductFunattri extends Model<ProductFunattri> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品PK
     */
    private String productKey;
    /**
     * 功能属性
     */
    private String funAttri;


    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getFunAttri() {
        return funAttri;
    }

    public void setFunAttri(String funAttri) {
        this.funAttri = funAttri;
    }

    @Override
    protected Serializable pkVal() {
        return this.productKey;
    }

    @Override
    public String toString() {
        return "ProductFunattri{" +
        "productKey=" + productKey +
        ", funAttri=" + funAttri +
        "}";
    }
}
