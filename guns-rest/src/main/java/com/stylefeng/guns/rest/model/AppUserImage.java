package com.stylefeng.guns.rest.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 用户头像
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-20
 */
@TableName("app_user_image")
public class AppUserImage extends Model<AppUserImage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "userId",type = IdType.INPUT)
    private String userId;
    private String fileName;
    private String contentType;
    private String fileKey;
    private String fileRealPath;
    private Long fileSize;
    private Date lastModify;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return this.userId;
    }

    @Override
    public String toString() {
        return "AppUserImage{" +
        "userId=" + userId +
        ", fileName=" + fileName +
        ", contentType=" + contentType +
        ", fileKey=" + fileKey +
        ", fileRealPath=" + fileRealPath +
        ", fileSize=" + fileSize +
        ", lastModify=" + lastModify +
        "}";
    }
}
