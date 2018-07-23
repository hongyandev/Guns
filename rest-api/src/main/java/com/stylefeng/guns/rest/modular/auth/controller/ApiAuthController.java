package com.stylefeng.guns.rest.modular.auth.controller;

import com.stylefeng.guns.rest.core.domain.ApiResult;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.ApiResultUtil;
import com.stylefeng.guns.rest.model.SecretKey;
import com.stylefeng.guns.rest.modular.auth.controller.dto.ApiRequest;
import com.stylefeng.guns.rest.modular.auth.util.RestToolUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IApiValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class ApiAuthController {

    @Resource(name = "apiValidator")
    private IApiValidator apiValidator;
    @Autowired
    private RestToolUtil restToolUtil;

    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public ApiResult<Object> authentication(@Valid ApiRequest apiRequest, BindingResult result) {
        if (result.hasErrors())
            return ApiResultUtil.failure(apiRequest, ResultEnum.CUSTOME_ERROR.getCode(), restToolUtil.errorTranslate(result));
        SecretKey secretKey = apiValidator.validate(apiRequest);
        return null;
    }
}
