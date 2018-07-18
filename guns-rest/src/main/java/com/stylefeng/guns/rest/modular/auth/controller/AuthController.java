package com.stylefeng.guns.rest.modular.auth.controller;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.service.IAppUserService;
import com.stylefeng.guns.rest.service.impl.AppUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
@RequestMapping(value = "/${jwt.auth-path}")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "dbValidator")
    private IReqValidator reqValidator;
    
    @Autowired
    private IAppUserService appUserServiceImpl;

    @RequestMapping(value = "/authentication")
    public Result<Object> authentication(AuthRequest authRequest) {

        AppUser appUser = reqValidator.validate(authRequest);

        if (Objects.nonNull(appUser)) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getCredence(), randomKey);
            //return ResponseEntity.ok(new AuthResponse(token, randomKey));
            return null;
        } else {
            throw new GunsException(ResultEnum.AUTH_REQUEST_ERROR);
        }
    }
    
    @RequestMapping(value = "/sms")
    public Result<Object> sms(String telephone){
    	return appUserServiceImpl.sendIcode(telephone);
    }
}
