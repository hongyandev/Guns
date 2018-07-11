package com.stylefeng.guns.rest.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class AuthzController {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@RequestMapping("/test")
	public ResponseEntity<?> test(){
		redisTemplate.opsForValue().set("guanqing", "好人");
		
		return ResponseEntity.ok(redisTemplate.opsForValue().get("guanqing"));
	}
	
}
