package org.jit.sose.util;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import sun.misc.BASE64Encoder;

public class MD5Util {

	/**
	 * 
	 * @param password
	 *            密码
	 * @param username
	 *            用户名
	 * @return 加密后的密码
	 */
	public static String md5(String password, String username) {
		return new Md5Hash(password, username).toString();
	}

	/**
	 * 利用MD5进行加密 @param str 待加密的字符串 @return 加密后的字符串 @t
	 * hrows
	 * NoSuchAlgorithmException 没有这种产生消息摘要的算法 @throws
	 * UnsupportedEncodingException
	 */
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newString = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newString;
	}

	/**
	 * 对字符串md5加密(小写+字母)
	 * 
	 * @param str
	 *            传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
	public static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
