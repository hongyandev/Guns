package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
@Data
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
	@TableId(value="productKey", type= IdType.INPUT)
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
     * 归属品类Key
     */
    private String categoryKey;
    /**
     * 归属品类Name
     */
    private String categoryName;
    /**
     * 访问方式
     */
    private String accessMethod;
    /**
     * 产品状态
     */
    private String status;
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
     * 连接模式
     */
    private String connectMode;
    /**
     * RBAC租户Id
     */
    private String rbacTenantId;
    /**
     * IoT平台
     */
    private Integer iotPackage;
    /**
     * useId2Auth
     */
    private Boolean useId2Auth;
    /**
     * 设备数量
     */
    private Integer deviceNumLimit;
    
    @TableField(exist=false)
    private List<ProductExtend> extendProperties;

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
        ", useId2Auth=" + useId2Auth +
        ", deviceNumLimit=" + deviceNumLimit +
        "}";
    }
}
