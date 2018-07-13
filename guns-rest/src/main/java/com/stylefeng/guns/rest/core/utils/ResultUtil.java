package com.stylefeng.guns.rest.core.utils;
/**
* create by guanqing
* 2018年7月12日 下午4:51:04
*/

import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;

public class ResultUtil {
	
	public static Result<Object> success(Object object){
		Result<Object> result = new Result<>();
		result.setCode(ResultEnum.ALI_SUCCESS_FLAG.getCode());
		result.setMsg(ResultEnum.ALI_SUCCESS_FLAG.getMessage());
		result.setData(object);
		return result;
	}
	
	public static Result<Object> success(){
		return success(null);
	}
	
	public static Result<Object> failure(Integer code,String msg){
		Result<Object> result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static Result<Object> failure(ResultEnum resultEnum){
		return failure(resultEnum.getCode(),resultEnum.getMessage());
	}
}
