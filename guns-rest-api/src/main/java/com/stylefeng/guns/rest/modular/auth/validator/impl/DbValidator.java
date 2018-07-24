package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.stylefeng.guns.rest.modular.auth.dao.SecretKeyMapper;
import com.stylefeng.guns.rest.modular.auth.model.SecretKey;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import com.stylefeng.guns.rest.core.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号密码验证
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class DbValidator implements IReqValidator {

	@Autowired
	SecretKeyMapper appUserMapper;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public SecretKey validate(Credence credence) {
		return null;
	}
}
