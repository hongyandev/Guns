package com.stylefeng.guns.rest.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.rest.model.UserIot;

/**
 * <p>
 * 用户设备表 服务类
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-25
 */
public interface IUserIotService extends IService<UserIot> {

	List<Map> listBinding(UserIot userIot);

}
