package com.stylefeng.guns.core.common.enums;

/**
 * Created by GQ
 * 2018年1月3日 下午5:21:50
 */

public enum ResultEnum {
    SUCCESS_FLAG("200", "成功"),
    ERROR_CUSTOME("500","自定义错误"),
    ;

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
