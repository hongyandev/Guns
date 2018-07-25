package com.stylefeng.guns.rest.core.utils;
/**
* create by guanqing
* 2018年7月12日 下午4:51:04
*/

import com.stylefeng.guns.rest.core.domain.ApiResult;
import com.stylefeng.guns.rest.core.enums.ApiResultEnum;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;

public class ApiResultUtil {
	
	public static ApiResult<Object> success(Credence request,Object object){
		ApiResult<Object> result = new ApiResult<>();
		result.setId(request.getId());
		result.setCode(ApiResultEnum.SUCCESS.getCode());
		result.setMessage(ApiResultEnum.SUCCESS.getMessage());
		result.setData(object);
		return result;
	}
	
	public static ApiResult<Object> success(Credence request){
		return success(request,null);
	}

	public static ApiResult<Object> failure(Credence request, Integer code, String message){
        ApiResult<Object> result = new ApiResult<>();
        result.setId(request.getId());
		result.setCode(code);
		result.setMessage(message);
		return result;
	}
	
	public static ApiResult<Object> failure(ApiResultEnum apiResultEnum, Credence request){
		return failure(request, apiResultEnum.getCode(), apiResultEnum.getMessage());
	}
}
