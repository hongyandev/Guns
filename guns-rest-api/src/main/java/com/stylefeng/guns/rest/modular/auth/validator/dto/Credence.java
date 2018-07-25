package com.stylefeng.guns.rest.modular.auth.validator.dto;


/**
 * 验证的凭据
 *
 * @author fengshuonan
 * @date 2017-08-27 13:27
 */
public interface Credence {

    String getId();

    String getAppKey();

    String getSign();

}
