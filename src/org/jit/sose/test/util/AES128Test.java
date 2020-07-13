package org.jit.sose.test.util;

import org.jit.sose.util.AES128Util;

public class AES128Test {
	public static void main(String[] args) {
		// 需要加密的内容
		String content = "123456";
		// 生成密钥需要的密码值
		String key = "dylan";

		System.out.println("content： " + content + "\nkey： " + key);
		// 内容加密后的值
		String encode = AES128Util.encrypt(content, key);
		System.out.println("加密后的内容：" + encode);

		// 被加密的内容解密后的值
		String decode = AES128Util.decrypt(encode, key);
		System.out.println("encode： " + encode + "\ndecode： " + decode);
		System.out.println("密码长度：" + encode.length());

		System.out.println("c236883fc0a3d7f6453a186283bbfc30".length());
		System.out.println(AES128Util.encrypt("1234567", "dylan2"));
		System.out.println(AES128Util.decrypt("c236883fc0a3d7f6453a186283bbfc30", "dylan2"));

	}
}
