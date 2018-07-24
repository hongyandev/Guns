package com.stylefeng.guns.rest.modular.auth.converter;

import lombok.Data;

/**
 * 基础的传输bean
 *
 * @author fengshuonan
 * @date 2017-08-25 15:52
 */
@Data
public class BaseTransferEntity {
    private String id;

    private String object; //base64编码的json字符串

    private String sign;   //签名

}
