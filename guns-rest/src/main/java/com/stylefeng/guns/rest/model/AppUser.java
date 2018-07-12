package com.stylefeng.guns.rest.model;

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
 * @author guanqing123
 * @since 2018-07-12
 */
@TableName("app_user")
public class AppUser extends Model<AppUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String userId;
    private String userName;
    private String phone;
    private String email;
    private String nickName;
    private String avatarUrl;
    private Date createDate;
    private Date updateDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "AppUser{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", phone=" + phone +
        ", email=" + email +
        ", nickName=" + nickName +
        ", avatarUrl=" + avatarUrl +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
