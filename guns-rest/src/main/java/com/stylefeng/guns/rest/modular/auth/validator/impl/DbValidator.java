package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import com.stylefeng.guns.rest.persistence.AppUserMapper;

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
	
	@Override
	public AppUser validate(Credence credence) {
		// TODO Auto-generated method stub
		AppUser user = null;
		switch (credence.getAuthType()) {
		case PASSWORD_TYPE:
			AppUser entity = new AppUser();
			entity.setUserName(credence.getCredenceName());
			entity.setPassword(credence.getCredenceCode());
			user = appUserMapper.selectOne(entity);
			break;
		case SMS_TYPE:
			
			break;
		default:
			break;
		}
		return user;
	}

    /*@Override
    public boolean validate(Credence credence) {
        List<AppUser> users = appUserMapper.selectList(new EntityWrapper<AppUser>().eq("userName", credence.getCredenceName()));
        if (users != null && users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }*/

}
