package com.stylefeng.guns.rest.service.impl;

import com.stylefeng.guns.rest.model.AppUser;
import com.stylefeng.guns.rest.persistence.AppUserMapper;
import com.stylefeng.guns.rest.service.IAppUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

}
