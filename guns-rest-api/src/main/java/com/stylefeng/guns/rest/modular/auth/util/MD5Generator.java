package com.stylefeng.guns.rest.modular.auth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.core.enums.ApiResultEnum;

/**
* create by guanqing
* 2018年7月16日 下午9:31:56
*/
public class MD5Generator {

	public static String generateVale() throws GunsException{
		return generateValue(UUID.randomUUID().toString());
	}
	

    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length*2);
        for ( byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) throws GunsException{
        try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(param.getBytes());
			byte[] messageDigest = algorithm.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GunsException(ApiResultEnum.AUTHCODE_ERROR);
		}
    }
}
