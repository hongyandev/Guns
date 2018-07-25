package com.stylefeng.guns.rest.persistence;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.model.UserIot;

/**
 * <p>
 * 用户设备表 Mapper 接口
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-25
 */
public interface UserIotMapper extends BaseMapper<UserIot> {

	List<Map> listBinding(UserIot userIot);

}
