package com.stylefeng.guns.rest.modular.auth.controller;

import com.aliyuncs.exceptions.ClientException;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.RedisUtil;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.controller.dto.*;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.util.MD5Generator;
import com.stylefeng.guns.rest.modular.auth.util.RestToolUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.service.IAppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
    private IAppUserService appUserServiceImpl;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private RestToolUtil restToolUtil;



    /**
     * 用户鉴权
     * @param authRequest
     * @param result
     * @return
     */
    @RequestMapping(value = "/authentication",method = RequestMethod.POST)
    public Result<Object> authentication(@Valid AuthRequest authRequest,BindingResult result) {
    	if (result.hasErrors())
    		return ResultUtil.failure
    				(ResultEnum.CUSTOME_ERROR.getCode(), restToolUtil.errorTranslate(result));
        AppUser appUser = reqValidator.validate(authRequest);
        if (Objects.nonNull(appUser)) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getCredence(), randomKey);
            //生成授权码
            String authCode = MD5Generator.generateVale();
            redisUtil.set(authCode, appUser, 10 * 60);
            AuthResponse authResponse = new AuthResponse(token, randomKey, authCode, appUser.getUserId());
            return ResultUtil.success(authResponse);
        } else {
            throw new GunsException(ResultEnum.AUTH_REQUEST_ERROR);
        }
    }

    /**
     * 用户token刷新
     * @param tokenRequest
     * @param result
     * @return
     */
    @RequestMapping(value = "/refreshToken",method = RequestMethod.POST)
    public Result<Object> refreshToken(@Valid TokenRequest tokenRequest,BindingResult result) {
    	if (result.hasErrors())
    		return ResultUtil.failure(ResultEnum.CUSTOME_ERROR.getCode(), restToolUtil.errorTranslate(result));
    	AppUser appUser = appUserServiceImpl.selectById(tokenRequest.getUserId());
    	if (Objects.isNull(appUser))
    		return ResultUtil.failure(ResultEnum.TOKEN_REFRESH_ERROR);
    	if(jwtTokenUtil.isTokenExpired(tokenRequest.getToken()))
    		return ResultUtil.failure(ResultEnum.TOKEN_EXPIRED);
    	String subject = jwtTokenUtil.getClaimFromToken(tokenRequest.getToken()).getSubject();
    	if(!tokenRequest.getUserId().equals(subject))
    		return ResultUtil.failure(ResultEnum.TOKEN_ERROR);
    	final String randomKey = jwtTokenUtil.getRandomKey();
    	final String token = jwtTokenUtil.generateToken(tokenRequest.getUserId(), randomKey);
    	TokenResponse tokenResponse = new TokenResponse(token, randomKey);
    	return ResultUtil.success(tokenResponse);
    }

    /**
     * 短信接口
     * @param smsRequest
     * @param result
     * @return
     */
    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    public Result<Object> sms(@Valid SmsRequest smsRequest,BindingResult result) {
    	if (result.hasErrors())
    		return ResultUtil.failure(ResultEnum.CUSTOME_ERROR.getCode(), restToolUtil.errorTranslate(result));
    	try {
			return appUserServiceImpl.sendIcode(smsRequest);
		} catch (ClientException e) {
			e.printStackTrace();
			return ResultUtil.failure(ResultEnum.CUSTOME_ERROR.getCode(), e.getMessage());
		}
    }

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<Object> register(AppUser user,String code) {
    	return appUserServiceImpl.register(user, code);
    }

    /**
     * 用户重置密码
     * @param user
     * @param code
     * @return
     */
    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
    public Result<Object> modifyPwd(AppUser user,String code) {
    	return appUserServiceImpl.modifyPwd(user, code);
    }
}
