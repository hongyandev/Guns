package com.stylefeng.guns.rest.modular.auth.controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 认证的响应结果
 *
 * @author guanqing
 * @Date 2018/7/16 21:58
 */
@Data
public class AuthResponse implements Serializable {

    /**
     * jwt token
     */
    private final String token;

	public AuthResponse(String token) {
        this.token = token;
    }
}
