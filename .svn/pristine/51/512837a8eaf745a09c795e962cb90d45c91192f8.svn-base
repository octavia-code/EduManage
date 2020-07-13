package org.jit.sose.config;

import java.util.Properties;

import org.jit.sose.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;

/**
 * 系统配置类
 * 
 * @author: 王越
 * @date: 2019-09-01 20:37:26
 */
@Configuration
public class SystemConfig {

	/**
	 * 文件上传地址
	 */
	public static String LOCALHOST_PATH;

	/**
	 * 项目名称
	 */
	public static String PROJECT_NAME;

	

	/**
	 * 资源文件路径
	 */
	private static String FILE_PROPERTIES = "resource/system.properties";

	public SystemConfig() {
		Properties prop = PropertiesUtil.getProperties(FILE_PROPERTIES);
		SystemConfig.LOCALHOST_PATH = prop.getProperty("LOCALHOST_PATH");
		SystemConfig.PROJECT_NAME = prop.getProperty("PROJECT_NAME");
	}

}
