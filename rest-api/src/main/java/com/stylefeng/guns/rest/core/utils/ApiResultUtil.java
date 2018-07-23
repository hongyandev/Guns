package com.stylefeng.guns.rest.core.utils;

import com.stylefeng.guns.rest.core.domain.ApiResult;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.modular.auth.controller.dto.ApiRequest;


public class ApiResultUtil {
    public static ApiResult<Object> success(ApiRequest apiRequest, Object object) {
        ApiResult<Object> result = new ApiResult<>();
        result.setId(apiRequest.getId());
        result.setCode(ResultEnum.SUCCESS_FLAG.getCode());
        result.setMsg(ResultEnum.SUCCESS_FLAG.getMessage());
        result.setData(object);
        return result;
    }

    public static ApiResult<Object> failure(ApiRequest apiRequest, Integer code, String message) {
        ApiResult<Object> result = new ApiResult<>();
        result.setId(apiRequest.getId());
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    public static ApiResult<Object> failure(ApiRequest apiRequest, ResultEnum resultEnum) {
        return failure(apiRequest, resultEnum.getCode(), resultEnum.getMessage());
    }
}
