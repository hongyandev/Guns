package com.stylefeng.guns.rest.persistence.service.impl;

import com.stylefeng.guns.rest.persistence.model.AppUser;
import com.stylefeng.guns.rest.persistence.dao.AppUserMapper;
import com.stylefeng.guns.rest.persistence.service.IAppUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-11
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

}
