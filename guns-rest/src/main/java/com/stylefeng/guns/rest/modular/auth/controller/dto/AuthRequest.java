package com.stylefeng.guns.rest.modular.auth.controller.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

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

	@NotBlank(message="参数credence为空")
	private String credence;
	@NotBlank(message="参数code为空")
	private String code;
	@Range(min=1,max=3,message="参数authType超出范围")
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
