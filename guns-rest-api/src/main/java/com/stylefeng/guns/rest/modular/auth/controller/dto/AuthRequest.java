package com.stylefeng.guns.rest.modular.auth.controller.dto;

import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 认证的请求dto
 *
 * @author guanqing
 * @Date 2018/7/13 14:00
 */
@Setter
public class AuthRequest implements Credence {

    private String id;
    @NotBlank
	private String appKey;
    @NotBlank
	private String sign;

    @Override
    public String getId() {
        return id;
    }

    @Override
	public String getAppKey() {
		return appKey;
	}

	@Override
	public String getSign() {
		return sign;
	}
}
