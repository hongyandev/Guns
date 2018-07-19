package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 产品图片
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-18
 */
@TableName("app_product_image")
public class ProductImage extends Model<ProductImage> {

    private static final long serialVersionUID = 1L;

    @TableId(value="productKey", type= IdType.INPUT)
    private String productKey;
    private String fileName;
    private String contentType;
    private String fileKey;
    private String fileRealPath;
    private Long fileSize;
    private Date lastModify;

	public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileRealPath() {
        return fileRealPath;
    }

    public void setFileRealPath(String fileRealPath) {
        this.fileRealPath = fileRealPath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

    @Override
    protected Serializable pkVal() {
        return this.productKey;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
        "productKey=" + productKey +
        ", fileName=" + fileName +
        ", contentType=" + contentType +
        ", fileKey=" + fileKey +
        ", fileRealPath=" + fileRealPath +
        ", fileSize=" + fileSize +
        ", lastModify=" + lastModify +
        "}";
    }
}
