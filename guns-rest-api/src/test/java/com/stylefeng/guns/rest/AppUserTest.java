package com.stylefeng.guns.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.rest.persistence.AppUserMapper;

/**
* create by guanqing
* 2018年7月20日 上午8:13:21
*/
public class AppUserTest extends BaseJunit {
	
	@Autowired
	private AppUserMapper appUserMapper;
	
	@Test
	public void T() {
		AppUser appUser = new AppUser();
		appUser.setUserId("d544c8bb-e98f-4771-9f2d-3c2e53e5f981");
		appUser.setPassword("abcdefg");
		appUser.updateById();
	}
	
	@Test
	public void TT() {
		AppUser appUser = new AppUser();
		appUser.setUserName("15857125375");
		appUser.setPassword("123456");
		appUserMapper.modifyPwd(appUser);
	}
	
	@Test
	public void TTT() {
		AppUser appUser = new AppUser();
		appUser.setUserName("15857125375");
		appUser.setPassword("888888");
		EntityWrapper<AppUser> entityWrapper = new EntityWrapper<>();
		Wrapper<AppUser> wrapper = entityWrapper.eq("userName", appUser.getUserName());
		appUser.update(wrapper);
	}
}
