package com.stylefeng.guns.rest.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户设备表
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-25
 */
@TableName("app_user_iot")
public class UserIot extends Model<UserIot> {

    private static final long serialVersionUID = 1L;

    /**
     * 物ID
     */
    private String iotId;
    /**
     * 产品Key
     */
    private String productKey;
    /**
     * 物昵称
     */
    private String nickName;
    /**
     * 0:分享者;1:拥有者
     */
    private String owned;
    /**
     * 状态
     */
    private String status;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 分组ID
     */
    private String groupId;
    /**
     * 场景ID
     */
    private String sceneId;
    /**
     * 绑定时间
     */
    private Date gmtModified;


    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        this.owned = owned;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    protected Serializable pkVal() {
        return this.iotId;
    }

    @Override
    public String toString() {
        return "UserIot{" +
        "iotId=" + iotId +
        ", productKey=" + productKey +
        ", nickName=" + nickName +
        ", owned=" + owned +
        ", status=" + status +
        ", userId=" + userId +
        ", groupId=" + groupId +
        ", sceneId=" + sceneId +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
