package com.stylefeng.guns.rest.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.model.UserIot;
import com.stylefeng.guns.rest.persistence.UserIotMapper;
import com.stylefeng.guns.rest.service.IUserIotService;

/**
 * <p>
 * 用户设备表 服务实现类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-25
 */
@Service
public class UserIotServiceImpl extends ServiceImpl<UserIotMapper, UserIot> implements IUserIotService {

	@Override
	public List<Map> listBinding(UserIot userIot) {
		return this.baseMapper.listBinding(userIot);
	}

}
