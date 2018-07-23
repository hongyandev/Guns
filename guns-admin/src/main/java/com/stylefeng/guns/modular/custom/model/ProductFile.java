package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产品附件
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-23
 */
@TableName("app_product_file")
public class ProductFile extends Model<ProductFile> {

    private static final long serialVersionUID = 1L;

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
        return "ProductFile{" +
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