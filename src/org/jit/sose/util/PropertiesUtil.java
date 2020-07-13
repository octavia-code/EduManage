package org.jit.sose.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建资源文件读取工具<br>
 * 创建Properties对象，实现对资源文件的读取
 * 
 * @author: 王越
 * @date: 2019-06-25 10:47:01
 */
@Slf4j
public class PropertiesUtil {

	// 私有化构造函数
	private PropertiesUtil() {
	}

	// 实例对象,指令重排序volatile
//	private static volatile Properties prop = null;
	private static  Properties prop = null;

	/**
	 * 获取Properties对象
	 * 
	 * @param propPath 配置文件路径
	 * @return Properties
	 */
	public static Properties getProperties(String propPath) {
		initPropertis(propPath);
//		if (prop == null) {
//			synchronized (PropertiesUtil.class) {
//				if (prop == null) {
//				}
//			}
//		}
		return prop;
	}

	/**
	 * 通过配置文件路径读取流，写入配置文件对象中
	 * 
	 * @param propPath 配置文件路径
	 */
	private static void initPropertis(String propPath) {
		// 创建配置文件对象
		prop = new Properties();
		// 创建流
		InputStream ins = null;
		try {
			// 读取文件，获取流
			File file = new File(propPath);
			if (file.canRead()) {
				ins = new BufferedInputStream(new FileInputStream(file));
			} else {
				// 读取路径，获取流
				ins = PropertiesUtil.class.getClassLoader().getResourceAsStream(propPath);
			}
			// 将XXX.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
			if (ins != null) {
				prop.load(ins);
			}
		} catch (Exception e) {
			log.error(propPath + "读取错误！");
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				if (ins != null) {
					ins.close();
				}
			} catch (IOException e) {
				log.error(propPath + "流关闭错误！");
			}
		}
	}
}
