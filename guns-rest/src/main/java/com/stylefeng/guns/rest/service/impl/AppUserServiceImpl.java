package com.stylefeng.guns.rest.service.impl;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.core.enums.ResultEnum;
import com.stylefeng.guns.rest.core.utils.ResultUtil;
import com.stylefeng.guns.rest.core.utils.SmsUtil;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.persistence.AppUserMapper;
import com.stylefeng.guns.rest.service.IAppUserService;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
	
	@Override
	public Result<Object> sendIcode(String telephone) {
		// TODO Auto-generated method stub
		if (!ToolUtil.isPhoneLegal(telephone))
			throw new GunsException(ResultEnum.TELEPHONE_INVALID);
		
		try {
			smsUtil.sendDySms(telephone, "SMS_109740618", "{\"code\":\""+smsUtil.getIcode(telephone)+"\"}", null);
			return ResultUtil.success();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtil.failure(ResultEnum.CUSTOME_ERROR.getCode(), e.getMessage());
		}
	}
}
