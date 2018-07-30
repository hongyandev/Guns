package com.stylefeng.guns.rest.modular.auth.controller;

import com.stylefeng.guns.core.util.RedisUtil;
import com.stylefeng.guns.rest.core.domain.ApiResult;
import com.stylefeng.guns.rest.modular.auth.service.ISecretKeyService;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.core.enums.ApiResultEnum;
import com.stylefeng.guns.rest.core.utils.ApiResultUtil;
import com.stylefeng.guns.rest.modular.auth.controller.dto.*;
import com.stylefeng.guns.rest.core.utils.RestToolUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 请求验证的
 *
 * @author guanqing
 * @Date 2018/7/16 21:58
 */
@RestController
@RequestMapping(value = "${jwt.auth-path}")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "dbValidator")
    private IReqValidator reqValidator;
    
    @Autowired
    private ISecretKeyService secretKeyService;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private RestToolUtil restToolUtil;


    @RequestMapping(value = "/getAccessToken",method = RequestMethod.POST)
    public ApiResult<Object> getAccessToken(@Valid AuthRequest request, BindingResult result) {
    	if (result.hasErrors())
    		return ApiResultUtil.failure
    				(request, ApiResultEnum.REQUEST_PARAMETER_ERROR.getCode(), restToolUtil.errorTranslate(result));
    	return null;
    }
}
