package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-14
 */
@TableName("app_product_extend")
public class ProductExtend extends Model<ProductExtend> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    @TableId(value="extendAttrId", type= IdType.INPUT)
    private Long extendAttrId;
    /**
     * 修改时间
     */
    private Date gmtModify;
    /**
     * 领域
     */
    private Integer domain;
    /**
     * 属性键
     */
    private String extendAttrKey;
    /**
     * 属性值
     */
    private String extendAttrValue;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 产品PK
     */
    private String productKey;
    /**
     * 属性名称
     */
    private String extendAttrName;


    public Long getExtendAttrId() {
        return extendAttrId;
    }

    public void setExtendAttrId(Long extendAttrId) {
        this.extendAttrId = extendAttrId;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getDomain() {
        return domain;
    }

    public void setDomain(Integer domain) {
        this.domain = domain;
    }

    public String getExtendAttrKey() {
        return extendAttrKey;
    }

    public void setExtendAttrKey(String extendAttrKey) {
        this.extendAttrKey = extendAttrKey;
    }

    public String getExtendAttrValue() {
        return extendAttrValue;
    }

    public void setExtendAttrValue(String extendAttrValue) {
        this.extendAttrValue = extendAttrValue;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getExtendAttrName() {
        return extendAttrName;
    }

    public void setExtendAttrName(String extendAttrName) {
        this.extendAttrName = extendAttrName;
    }

    @Override
    protected Serializable pkVal() {
        return this.extendAttrId;
    }

    @Override
    public String toString() {
        return "ProductExtend{" +
        "extendAttrId=" + extendAttrId +
        ", gmtModify=" + gmtModify +
        ", domain=" + domain +
        ", extendAttrKey=" + extendAttrKey +
        ", extendAttrValue=" + extendAttrValue +
        ", gmtCreate=" + gmtCreate +
        ", productKey=" + productKey +
        ", extendAttrName=" + extendAttrName +
        "}";
    }
}
