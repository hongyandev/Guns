package com.stylefeng.guns.rest.core.utils;
/**
* create by guanqing
* 2018年7月12日 下午4:51:04
*/

import com.stylefeng.guns.rest.core.domain.ApiResult;
import com.stylefeng.guns.rest.core.enums.ApiResultEnum;

public class ApiResultUtil {
	
	public static ApiResult<Object> success(Object object){
		ApiResult<Object> result = new ApiResult<>();
		result.setCode(ApiResultEnum.SUCCESS.getCode());
		result.setMessage(ApiResultEnum.SUCCESS.getMessage());
		result.setData(object);
		return result;
	}
	
	public static ApiResult<Object> success(){
		return success(null);
	}

	public static ApiResult<Object> failure(Integer code, String message){
        ApiResult<Object> result = new ApiResult<>();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}
	
	public static ApiResult<Object> failure(ApiResultEnum apiResultEnum){
		return failure(apiResultEnum.getCode(), apiResultEnum.getMessage());
	}
}
