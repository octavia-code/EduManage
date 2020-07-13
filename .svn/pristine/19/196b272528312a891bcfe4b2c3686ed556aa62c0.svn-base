package org.jit.sose.config;

import java.io.File;
import java.util.Properties;

import org.jit.sose.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件上传配置类
 * 
 * @author: 王越
 * @date: 2019-06-28 14:14:01
 */
@Slf4j
@Configuration
public class FileConfig {

	/**
	 * 文件上传地址
	 */
	public static String UPLOAD_PATH;

	/**
	 * 虚拟目录
	 */
	public static String VIRTUAL_PATH;

	/**
	 * 异常日志文件目录
	 */
	public static String LOG_EXCEPTION_PATH;

	/**
	 * 资源文件路径
	 */
	private static String FILE_PROPERTIES = "resource/file.properties";

	public FileConfig() {
		Properties prop = PropertiesUtil.getProperties(FILE_PROPERTIES);
		FileConfig.UPLOAD_PATH = prop.getProperty("UPLOAD_PATH");
		FileConfig.VIRTUAL_PATH = prop.getProperty("VIRTUAL_PATH");
		FileConfig.LOG_EXCEPTION_PATH = prop.getProperty("LOG_EXCEPTION_PATH");

		// 创建文件路径
		File exceptionFile = new File(FileConfig.LOG_EXCEPTION_PATH);
		// 若文件
		if (!exceptionFile.exists()) {
			log.info("*********创建异常日志文件夹*********");
			exceptionFile.mkdirs();
		}

	}

}
