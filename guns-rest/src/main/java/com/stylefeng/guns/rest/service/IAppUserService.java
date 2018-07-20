package com.stylefeng.guns.rest.service;

import com.stylefeng.guns.rest.core.domain.Result;
import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Smsdence;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
public interface IAppUserService extends IService<AppUser> {
	
	public Result<Object> sendIcode(Smsdence smsdence) throws ClientException ;

	public Result<Object> register(AppUser user, String code);

	public Result<Object> modifyPwd(AppUser user, String code);
}
