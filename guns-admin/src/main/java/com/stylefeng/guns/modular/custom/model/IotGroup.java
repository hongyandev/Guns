package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备分组表
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-25
 */
@TableName("app_iot_group")
public class IotGroup extends Model<IotGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 分组ID
     */
    private String groupId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 分组名
     */
    private String groupName;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    protected Serializable pkVal() {
        return this.groupId;
    }

    @Override
    public String toString() {
        return "IotGroup{" +
        "groupId=" + groupId +
        ", userId=" + userId +
        ", groupName=" + groupName +
        "}";
    }
}
