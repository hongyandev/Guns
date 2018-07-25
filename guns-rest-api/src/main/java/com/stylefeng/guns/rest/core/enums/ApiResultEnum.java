package com.stylefeng.guns.rest.core.enums;

import com.stylefeng.guns.core.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author guanqing
 * @date 2018年07月12日 下午5:04:51
 */
public enum ApiResultEnum implements ServiceExceptionEnum {

	SUCCESS(200, "成功"),
	REQUEST_ERROR(400, "请求错误"),
	REQUEST_AUTH_ERROR(401, "请求认证错误"),
	REQUEST_FORBIDDEN(403, "请求被禁止"),
	SERVICE_NOT_FOUND(404, "服务未找到"),
	TOO_MANY_REQUESTS(429, "太多请求"),
	REQUEST_PARAMETER_ERROR(460, "请求参数错误"),
	SERVICE_ERROR(500, "服务端错误"),
	SERVICE_NOT_AVAILABLE(503, "服务不可用"), 
	SIGN_ERROR(402, "签名验证错误");

	ApiResultEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private Integer code;

	private String message;

	@Override
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
