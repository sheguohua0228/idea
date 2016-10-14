package com.leyes.app.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

/**
 * MD5加密类
* @TypeName: MD5Encrypt 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年9月5日 上午11:21:58 
*
 */
public class MD5Encrypt {
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    
	/**
	 * 对字符串进行MD5加密
	 * @param text 明文
	 * @return 密文
	 */
	public static String md5(String text) {
		return md5(text,Charset.forName("utf8"));
	}
	
	/**
	 * 对字符串进行MD5加密
	 * @param text 明文
	 * @return 密文
	 */
	public static String md5(String text,Charset charset) {
		MessageDigest msgDigest = null;

		try {
			msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes(charset));    //按照utf-8编码形式加密
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}

}