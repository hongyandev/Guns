package com.stylefeng.guns.rest.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("sys_secret_key")
public class SecretKey extends Model<SecretKey> {

    @TableId(value = "appKey", type = IdType.INPUT)
    private int appKey;
    private String appSecret;
    private int state;
    private String appName;

    @Override
    protected Serializable pkVal() {
        return this.appKey;
    }

    @Override
    public String toString() {
        return "SecretKey{" +
                "appKey=" + appKey +
                ", appSecret=" + appSecret +
                ", state=" + state +
                ", appName=" + appName +
                "}";
    }
}
