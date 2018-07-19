package com.stylefeng.guns.rest.modular.auth.controller;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.RedisUtil;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.util.MD5Generator;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.service.IAppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import javax.annotation.Resource;

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
    private IAppUserService appUserServiceImpl;
    
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/authentication")
    public Result<Object> authentication(AuthRequest authRequest) {
        AppUser appUser = reqValidator.validate(authRequest);
        if (Objects.nonNull(appUser)) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getCredence(), randomKey);
            //生成授权码
            String authCode = MD5Generator.generateVale();
            redisUtil.set(authCode, appUser, 10 * 60);
            AuthResponse authResponse = new AuthResponse(token, randomKey, authCode);
            return ResultUtil.success(authResponse);
        } else {
            throw new GunsException(ResultEnum.AUTH_REQUEST_ERROR);
        }
    }
    
    @RequestMapping(value = "/sms")
    public Result<Object> sms(String telephone){
    	return appUserServiceImpl.sendIcode(telephone);
    }
}
