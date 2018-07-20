package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.RedisUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import com.stylefeng.guns.rest.persistence.AppUserMapper;

import java.util.Objects;

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
	AppUserMapper appUserMapper;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public AppUser validate(Credence credence) {
		// TODO Auto-generated method stub
		AppUser user = null;
		switch (credence.getAuthType()) {
		case PASSWORD_TYPE:{
				AppUser entity = new AppUser();
				entity.setUserName(credence.getCredenceName());
				entity.setPassword(credence.getCredenceCode());
				user = appUserMapper.selectOne(entity);
				break;
			}
		case SMS_TYPE:{
				String codeKey = "getLoginIcode." + credence.getCredenceName();
				Object icode = redisUtil.get(codeKey);
				if (Objects.isNull(icode) || !icode.equals(credence.getCredenceCode()))
					throw new GunsException(ResultEnum.CODE_INVALID);
				redisUtil.remove(codeKey);
				AppUser entity = new AppUser();
				entity.setUserName(credence.getCredenceName());
				user = appUserMapper.selectOne(entity);
				break;
			}
		default:
			break;
		}
		return user;
	}
}
