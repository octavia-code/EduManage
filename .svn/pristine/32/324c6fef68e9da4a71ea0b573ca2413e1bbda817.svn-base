package org.jit.sose.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES128加密工具类
 * 
 * @author: dylan   
 * @date: 2019-09-10 22:59:09
 */
public class AES128Util {

	/**
	 * 加密
	 * 
	 * @param content 要加密的内容
	 * @param key     用来生成128位密钥的密码
	 * @return
	 */
	public static String encrypt(String content, String key) {
		try {
			/**
			 * KeyGenerator : 是个类，此类提供（对称）密钥生成器的功能。在生成密钥后，可以重复使用同一个 KeyGenerator 对象来生成更多的密钥。
			 * 使用其中的getInstance（String algorithm）方法进行构造对象； algorithm ： 所请求密钥算法的标准名称。
			 */
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			/**
			 * Random : 此类的实例用于生成伪随机数流。 SecureRandom : 是Random的直接子类。此类提供强加密随机数生成器
			 * (RNG)。还必须生成非确定性输出（而）。 SecureRandom(byte[] seed) : 构造一个实现默认随机数算法的安全随机数生成器
			 * (RNG)。使用指定的种子字节设置种子。 init(int keysize, SecureRandom random) :
			 * 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。 keysize : 密钥大小。这是特定于算法的一种规格，是以位数为单位指定的。
			 * random : 此密钥生成器的随机源
			 */
			kgen.init(128, new SecureRandom(key.getBytes()));
			/**
			 * java.security包中有接口 Key，SecretKey是Key的子接口，SecretKeySpec是SecretKey的实现类。 Key :
			 * Key 是所有密钥的顶层接口。它定义了供所有密钥对象共享的功能。 SecretKey :
			 * 此接口不包含方法或常量。其唯一目的是分组秘密密钥（并为其提供类型安全）。 SecretKeySpec : 可以使用此类来根据一个字节数组构造一个
			 * SecretKey
			 * 
			 * generateKey() : 生成一个密钥。
			 */
			SecretKey secretKey = kgen.generateKey();
			/**
			 * getEncoded() : 是Key接口中的方法；返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null。
			 */
			byte[] enCodeFormat = secretKey.getEncoded();
			/**
			 * SecretKeySpec(byte[] key, String algorithm) :
			 * SecretKeySpec的构造方法之一，根据给定的字节数组构造一个密钥。 key : 密钥的密钥内容。复制该数组的内容来防止后续修改。
			 * algorithm : 与给定的密钥内容相关联的密钥算法的名称。
			 */
			SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
			/**
			 * Cipher : 该类为加密和解密提供加密密码功能。 getInstance(String transformation) :
			 * 通过指定转换模式的方式获得实例化对象。 transformation : 转换的名称，例如:AES 或者
			 * DES/CBC/PKCS5Padding。转换始终包括加密算法的名称（例如，DES），后面可能跟有一个反馈模式和填充方案。
			 */
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("utf-8");
			/**
			 * init(int opmode, Key key) : 用密钥初始化此 cipher。为以下 4 种操作之一初始化该
			 * cipher：加密、解密、密钥包装或密钥打开，这取决于 opmode 的值。 opmode : 此 cipher
			 * 的操作模式（其为如下之一：ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE 或 UNWRAP_MODE） key : 密钥
			 */
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			/**
			 * doFinal(byte[] input) : 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此 Cipher
			 * 的初始化模式）。 input : 输入的数组，即：要加密或解密的内容
			 */
			byte[] result = cipher.doFinal(byteContent);
			/**
			 * parseByte2HexStr(String result) : 自定义的一套将二进制数据转换为十六进制的数据的方法；
			 *
			 * ！注：在这儿，加密后的byte数组是不能强制转换成字符串的(即：new String（result）);
			 * 换言之,字符串和byte数组在这种情况下不是互逆的。 处理方式有两种： 1.将result转化为十六进制的数据再做处理（需要自己写一个转换方法）；
			 * 2.将result进行Base64(也可以用 Base64)再次加密在进行强制转换（不需要自己写方法，省事儿）。
			 */
			return parseByte2HexStr(result);
			// return new String(Base64.encode(result));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content 要解密的内容
	 * @param key     用来生成128位密钥的密码
	 * @return
	 */
	public static String decrypt(String content, String key) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(key.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
			byte[] result = cipher.doFinal(parseHexStr2Byte(content));
//			byte[] result = cipher.doFinal(Base64.decode(content.getBytes()));
			return new String(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制数组 ——> 十六进制数字
	 * 
	 * @param buf 要转换的数组
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			/**
			 * Integer : 该类在对象中包装了一个基本类型 int 的值（即：Integer是int的包装类）。该类提供了多个方法，能在 int 类型和
			 * String 类型之间互相转换，还提供了处理 int 类型时非常有用的其他一些常量和方法。 toHexString(int i) :
			 * 以十六进制的无符号整数形式返回一个整数参数的字符串表示形式。 i : 要转换成字符串的整数。 buf[i] & 0xFF :
			 * 将字节数组中每个字节拆解成2位16进制整数(原因是：每个字节(即：byte)占8位(即：bit)，16进制的基数是由4位组成)
			 */
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				/**
				 * 因为toHexString(int i)将需要转换的i值转换为十六进制（基数 16）的无前导 0 的 ASCII 数字字符串，所以要重新加上
				 */
				hex = '0' + hex;
			}
			/**
			 * toUpperCase() : 使用默认语言环境的规则将此 String 中的所有字符都转换为大写。
			 */
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将十六进制数字 ——> 二进制数组
	 * 
	 * @param hexStr 要转换的数组
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		/**
		 * hexStr.length()/2 :
		 * 将每2位16进制整数组装成一个字节(原因是：每个字节(即：byte)占8位(即：bit)，16进制的基数是由4位组成)
		 */
		for (int i = 0; i < hexStr.length() / 2; i++) {
			/**
			 * parseInt(String s, int radix) : 使用第二个参数指定的基数，将字符串参数解析为有符号的整数。 s :
			 * 包含要分析的整数表示形式的 String。 radix : 分析 s 时使用的基数。 如果发生以下任意一种情况，则抛出一个
			 * NumberFormatException 类型的异常： 1、第一个参数为 null 或一个长度为零的字符串。 2、基数小于
			 * Character.MIN_RADIX 或者大于 Character.MAX_RADIX。 3、假如字符串的长度超过 1，那么除了第一个字符可以是减号
			 * '-' ('u002D’) 外，字符串中的任何字符都不是指定基数的数字(即：第二个参数radix)。 4、字符串表示的值不是 int 类型的值。
			 */
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			/**
			 * String : 该类代表字符串。Java 程序中的所有字符串字面值（如 "abc" ）都作为此类的实例来实现。 substring(int
			 * beginIndex, int endIndex) : 返回一个新字符串，它是此字符串的一个子字符串。该子字符串从指定的 beginIndex
			 * 处开始，一直到索引 endIndex - 1 处的字符。 beginIndex : 开始处的索引（包括）。 endIndex : 结束处的索引（不包括）。
			 */
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	

}
