package com.stylefeng.guns.jwt;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.util.IdGenerator;
import com.stylefeng.guns.rest.core.config.properties.Oauth2Properties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * jwt测试
 *
 * @author guanqing
 * @date 2018-07-17 11:04
 */
public class JWTTest extends BaseJunit{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private Oauth2Properties oauth2properties;
    
    @Test
    public void TTTT() {
    	String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkNTQ0YzhiYi1lOThmLTQ3NzEtOWYyZC0zYzJlNTNlNWY5ODEiLCJpYXQiOjE1MzE4OTI5ODEsImV4cCI6MTUzMTk3OTM4MX0.iJCjcxBjNisrPSbKOgb8_K86JCj1QEFme1NpVc4vEX9UcqDbpeeuEWXMAVS9a4JnRM3SdeRc5Q93llkFxHzSrg";
    	System.out.println("subject>>"+jwtTokenUtil.getClaimFromToken(token).getSubject());
    	System.out.println("expiration>>"+jwtTokenUtil.getClaimFromToken(token).getExpiration());
    	String refreshToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkNTQ0YzhiYi1lOThmLTQ3NzEtOWYyZC0zYzJlNTNlNWY5ODEiLCJpYXQiOjE1MzE4OTI5ODEsImV4cCI6MTUzNDQ4NDk4MX0.tMIh3v08MkqWncA-awoXyhgTAukQ-GrufDcNjM-LrR6EHw77zbuqfan49yaD1Ge1_twOPtuQKe5sadkeqQCs3Q";
    	System.out.println("refresh_subject>>"+jwtTokenUtil.getClaimFromToken(refreshToken).getSubject());
    	System.out.println("refresh_expiration>>"+jwtTokenUtil.getClaimFromToken(refreshToken).getExpiration());
    	System.out.println("refresh_token flag>>"+jwtTokenUtil.isTokenExpired(refreshToken));
    }
    
    @Test
    public void TTT() {
    	System.out.println(oauth2properties.getAppKey());
    }
    
    @Test
	public void T() throws InterruptedException{
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + 60000);

        Map<String, Object> claim = new HashMap<>();
        claim.put("accound", "13701");
        
		String abc = Jwts.builder()
        .setClaims(claim)
        .setSubject("guanqing")
        .setIssuedAt(createdDate)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, "mySecret")
        .compact();
        System.out.println(abc);
		System.out.println(jwtTokenUtil.getExpirationDateFromToken(abc));
		System.out.println(jwtTokenUtil.getClaimFromToken(abc).get("accound"));
		Thread.sleep(3000);
		System.out.println(jwtTokenUtil.isTokenExpired(abc));
	}
	
    public void TT() {
    	
        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
                .setSubject("Joe")
                .setClaims(new DefaultClaims().setId(IdGenerator.getId()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println(compactJws);


        assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals("Joe");

        try {
            Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody();
            System.out.println(body);
            System.out.println(body.getExpiration());

            System.out.println("trust");
        } catch (SignatureException e) {
            System.out.println("not trust");
        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException");
        }
    }
}
