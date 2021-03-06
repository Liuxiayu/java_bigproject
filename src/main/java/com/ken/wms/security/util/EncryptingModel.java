package com.ken.wms.security.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class EncryptingModel {

	/**
	 * 瀵瑰瓧绗︿覆杩涜 MD5 鍔犲瘑
	 * @param plainString 闇�瑕佸姞瀵嗙殑瀛楃涓�
	 * @return 杩斿洖鍔犲瘑鍚庣殑瀛楃涓�
	 * @throws NoSuchAlgorithmException 
	 */
	public String MD5(String plainString) throws NoSuchAlgorithmException,NullPointerException{
		
		if(plainString == null)
			throw new NullPointerException();
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainString.getBytes());
		byte[] byteData = messageDigest.digest();
		
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		
		return hexString.toString();
	}
}
