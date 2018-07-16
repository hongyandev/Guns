package com.stylefeng.guns.rest.modular.auth.controller.dto;

import com.stylefeng.guns.rest.modular.auth.enums.AuthType;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;

import lombok.Getter;
import lombok.Setter;

/**
 * 认证的请求dto
 *
 * @author guanqing
 * @Date 2018/7/13 14:00
 */
@Getter
@Setter
public class AuthRequest implements Credence {

	private String credence;
	private String code;
	private Integer authType;

    @Override
    public String getCredenceName() {
        return this.credence;
    }

    @Override
    public String getCredenceCode() {
        return this.code;
    }

	@Override
	public AuthType getAuthType() {
		// TODO Auto-generated method stub
		return AuthType.getAuthType(this.authType);
	}
}
