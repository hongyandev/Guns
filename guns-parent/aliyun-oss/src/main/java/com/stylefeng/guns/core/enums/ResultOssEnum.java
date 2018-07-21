package com.stylefeng.guns.core.enums;

import com.stylefeng.guns.core.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author guanqing
 * @date 2018年07月12日 下午5:04:51
 */
public enum ResultOssEnum implements ServiceExceptionEnum {

	SUCCESS_FLAG(200,"成功"),
	FILE_NOT_FOUND(400007,"未找到文件"),
	FILE_UPLOAD_ERROR(400008,"文件上传异常");

	ResultOssEnum(int code, String message) {
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
