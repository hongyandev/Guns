package com.stylefeng.guns.rest.service.impl;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.RedisUtil;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.core.utils.SmsUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.enums.RegisterType;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Smsdence;
import com.stylefeng.guns.rest.persistence.AppUserMapper;
import com.stylefeng.guns.rest.service.IAppUserService;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

	@Autowired
	private SmsUtil smsUtil;
	
	@Autowired
	private AppUserMapper appUserMapper;
	
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public Result<Object> sendIcode(Smsdence smsdence) throws ClientException {
		// TODO Auto-generated method stub
		if (!ToolUtil.isPhoneLegal(smsdence.getSmsdenceName()))
			throw new GunsException(ResultEnum.TELEPHONE_INVALID);
		
		EntityWrapper<AppUser> entityWrapper = new EntityWrapper<>();
		Wrapper<AppUser> wrapper = entityWrapper.eq("userName", smsdence.getSmsdenceName());
		Integer count = appUserMapper.selectCount(wrapper);
		
		switch (smsdence.getSmsType()) {
		case REGISTER_TYPE:
			if (count > 0)
				return ResultUtil.failure(ResultEnum.TELEPHONE_REGISTED);
			smsUtil.sendDySms(smsdence.getSmsdenceName(), "SMS_139976382", "{\"code\":\""+smsUtil.getRegisterIcode(smsdence.getSmsdenceName())+"\"}", null);
			break;
		case LOGIN_TYPE:
			if (count < 1)
				return ResultUtil.failure(ResultEnum.TELEPHONE_NOREGISTED);
			smsUtil.sendDySms(smsdence.getSmsdenceName(), "SMS_139986222", "{\"code\":\""+smsUtil.getLoginIcode(smsdence.getSmsdenceName())+"\"}", null);
			break;
		case MODIFY_TYPE:
			if (count < 1)
				return ResultUtil.failure(ResultEnum.TELEPHONE_NOREGISTED);
			smsUtil.sendDySms(smsdence.getSmsdenceName(), "SMS_139976396", "{\"code\":\""+smsUtil.getModifyIcode(smsdence.getSmsdenceName())+"\"}", null);
			break;
		default:
			return ResultUtil.failure(ResultEnum.CUSTOME_ERROR.getCode(),"短信类别出错");
		}
		return ResultUtil.success();
	}

	@Override
	public Result<Object> register(AppUser user, String code) {
		// TODO Auto-generated method stub
		EntityWrapper<AppUser> entityWrapper = new EntityWrapper<>();
		Wrapper<AppUser> wrapper = entityWrapper.eq("userName", user.getUserName());
		Integer count = appUserMapper.selectCount(wrapper);
		if (count > 0)
			return ResultUtil.failure(ResultEnum.TELEPHONE_REGISTED);
		String codeKey = "getRegisterIcode." + user.getUserName();
		Object icode = redisUtil.get(codeKey);
		if(Objects.nonNull(icode) && icode.equals(code)) {
			user.setPhone(user.getUserName());
			user.setLoginSource(RegisterType.TELEPHONE_TYPE.getType());
			Date current = new Date();
			user.setCreateDate(current);
			user.setUpdateDate(current);
			user.setUserId(ToolUtil.uuid());
			user.insert();
			redisUtil.remove(codeKey);
			return ResultUtil.success();
		}else {
			return ResultUtil.failure(ResultEnum.CODE_INVALID);
		}
	}

	@Override
	public Result<Object> modifyPwd(AppUser user, String code) {
		// TODO Auto-generated method stub
		EntityWrapper<AppUser> entityWrapper = new EntityWrapper<>();
		Wrapper<AppUser> wrapper = entityWrapper.eq("userName", user.getUserName());
		Integer count = appUserMapper.selectCount(wrapper);
		if (count < 1)
			return ResultUtil.failure(ResultEnum.TELEPHONE_NOREGISTED);
		String codeKey = "getModifyIcode." + user.getUserName();
		Object icode = redisUtil.get(codeKey);
		if(Objects.nonNull(icode) && icode.equals(code)) {
			appUserMapper.modifyPwd(user);
			redisUtil.remove(codeKey);
			return ResultUtil.success();
		}else {
			return ResultUtil.failure(ResultEnum.CODE_INVALID);
		}
	}
}
