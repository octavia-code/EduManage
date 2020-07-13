package org.jit.sose.config;

import java.util.Properties;

import org.jit.sose.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;

/**
 * JWT 配置类
 * 
 * @author: 王越
 * @date: 2019-09-11 14:54:11
 */
@Configuration
public class JWTConfig {

	/**
	 * 密钥
	 */
	public static String SECRET;

	/**
	 * 签发人
	 */
	public static String ISS;
	/**
	 * 资源文件路径
	 */
	private static String JWT_PROPERTIES = "resource/jwt.properties";

	public JWTConfig() {
		Properties prop = PropertiesUtil.getProperties(JWT_PROPERTIES);
		JWTConfig.SECRET = prop.getProperty("SECRET");
		JWTConfig.ISS = prop.getProperty("ISS");
	}

}
