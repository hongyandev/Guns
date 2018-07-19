package com.stylefeng.guns.rest.core.enums;

import com.stylefeng.guns.core.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author guanqing
 * @date 2018年07月12日 下午5:04:51
 */
public enum ResultEnum implements ServiceExceptionEnum {

	/** 自有账号体系始 */
	ALI_SUCCESS_FLAG(200, "成功"), 
	ALI_TOKEN_EXPIRED(100001, "AccessToken过期"), 
	ALI_CODE_ERROR(100002, "用code换取access token值失败"), 
	ALI_REFRESH_TOKEN_EXPIRED(100003, "RefreshToken已过期或已失效"), 
	ALI_REVOKE_TOKEN_INVALID(100004, "用户改密或解除授权导致AccessToken失效"), 
	ALI_TOKEN_ILLEGAL(100005, "AccessToken非法"), 
	ALI_OPENID_INVALID(100006, "无效的OpenId"),
	ALI_COMMON_ERROR(110000, "系统通用错误(无法--列出其他所有错误,需联系三方ISV)"),
	/** 自有账号体系终 */

	/**
	 * token异常
	 */
	TOKEN_EXPIRED(700001, "token过期"),
	TOKEN_ERROR(700002, "token验证失败"),

	/**
	 * 签名异常
	 */
	SIGN_ERROR(700003, "签名验证失败"),

	/**
	 * 其他
	 */
	AUTH_REQUEST_ERROR(400001, "账号密码错误"),
	TELEPHONE_INVALID(400002, "手机号码无效"), 
	CODE_INVALID(400003, "验证码无效"), 
	AUTHCODE_ERROR(400004, "authCode生成异常"),
	CUSTOME_ERROR(400000, "自定义错误");

	ResultEnum(int code, String message) {
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
