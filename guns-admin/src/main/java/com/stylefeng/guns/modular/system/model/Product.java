package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-12
 */
@TableName("app_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 产品PK
     */
    private String productKey;
    /**
     * 数据格式
     */
    private String dataFormat;
    /**
     * 入网类型
     */
    private String netType;
    /**
     * 产品密钥
     */
    private String productSecret;
    /**
     * 节点类型
     */
    private String nodeType;
    /**
     * 领域
     */
    private String domain;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 地域
     */
    private String region;
    /**
     * 所有者领域
     */
    private String ownerDomain;
    /**
     * 归属品类id
     */
    private Long categoryld;
    /**
     * categoryKey
     */
    private String categoryKey;
    /**
     * categoryName
     */
    private String categoryName;
    /**
     * 访问方式
     */
    private String accessMethod;
    /**
     * 产品状态(0:开发中,1:已发布)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 商品码
     */
    private String aliyunCommodityCode;
    /**
     * connectMode
     */
    private String connectMode;
    /**
     * rbac租户Id
     */
    private String rbacTenantId;
    /**
     * IoT平台
     */
    private Integer iotPackage;


    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getProductSecret() {
        return productSecret;
    }

    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOwnerDomain() {
        return ownerDomain;
    }

    public void setOwnerDomain(String ownerDomain) {
        this.ownerDomain = ownerDomain;
    }

    public Long getCategoryld() {
        return categoryld;
    }

    public void setCategoryld(Long categoryld) {
        this.categoryld = categoryld;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAliyunCommodityCode() {
        return aliyunCommodityCode;
    }

    public void setAliyunCommodityCode(String aliyunCommodityCode) {
        this.aliyunCommodityCode = aliyunCommodityCode;
    }

    public String getConnectMode() {
        return connectMode;
    }

    public void setConnectMode(String connectMode) {
        this.connectMode = connectMode;
    }

    public String getRbacTenantId() {
        return rbacTenantId;
    }

    public void setRbacTenantId(String rbacTenantId) {
        this.rbacTenantId = rbacTenantId;
    }

    public Integer getIotPackage() {
        return iotPackage;
    }

    public void setIotPackage(Integer iotPackage) {
        this.iotPackage = iotPackage;
    }

    @Override
    protected Serializable pkVal() {
        return this.productKey;
    }

    @Override
    public String toString() {
        return "Product{" +
        "tenantId=" + tenantId +
        ", productKey=" + productKey +
        ", dataFormat=" + dataFormat +
        ", netType=" + netType +
        ", productSecret=" + productSecret +
        ", nodeType=" + nodeType +
        ", domain=" + domain +
        ", name=" + name +
        ", region=" + region +
        ", ownerDomain=" + ownerDomain +
        ", categoryld=" + categoryld +
        ", categoryKey=" + categoryKey +
        ", categoryName=" + categoryName +
        ", accessMethod=" + accessMethod +
        ", status=" + status +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", creator=" + creator +
        ", modifier=" + modifier +
        ", productId=" + productId +
        ", aliyunCommodityCode=" + aliyunCommodityCode +
        ", connectMode=" + connectMode +
        ", rbacTenantId=" + rbacTenantId +
        ", iotPackage=" + iotPackage +
        "}";
    }
}
