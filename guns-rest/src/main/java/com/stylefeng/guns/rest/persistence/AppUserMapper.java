package com.stylefeng.guns.rest.persistence;

import com.stylefeng.guns.rest.model.AppUser;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-12
 */
@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {

	void modifyPwd(AppUser user);

}
