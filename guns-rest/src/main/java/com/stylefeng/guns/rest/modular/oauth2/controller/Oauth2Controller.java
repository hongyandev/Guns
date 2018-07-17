package com.stylefeng.guns.rest.modular.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stylefeng.guns.rest.core.utils.RedisUtil;

@Controller
@RequestMapping("/oauth2")
public class AuthzController {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping("/test")
	public ResponseEntity<?> test(){
		
		/*AppUser user = new AppUser();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName("官青");
		user.setNickName("战斗圣佛");
		
		redisUtil.set(user.getUserId(), user, 600);
		
		AppUser appUser = (AppUser) redisUtil.get(user.getUserId());
		System.out.println("user2 >> "+appUser.toString());
		
		return ResponseEntity.ok(redisUtil.get(user.getUserId()));*/
		return ResponseEntity.ok(new Object());
	}
	
}
