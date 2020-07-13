package org.jit.sose.config;

import java.util.Properties;

import org.jit.sose.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云短信配置项
 * 
 * @author: 王越
 * @date: 2019-06-20 10:46:24
 */
@Configuration // 表明这是一个配置类，
public class QcloudSmsConfig {

	/**
	 * 短信应用SDK appId
	 */
	public static Integer appId;

	/**
	 * 短信应用SDK appKey
	 */
	public static String appKey;

	/**
	 * 签名使用的是`签名内容`，而不是`签名ID`
	 */
	public static String smsSign;

	/**
	 * 自定义短信验证码的长度
	 */
	public static Integer smsCodeLength;

	/**
	 * 注册模板ID
	 */
	public static Integer registerTemplateId;

	/**
	 * 忘记密码模板ID
	 */
	public static Integer forgetPwdTemplateId;

	/**
	 * 登录模板ID
	 */
	public static Integer loginTemplateId;

	/**
	 * 注册验证码有效时间,单位分钟
	 */
	public static String registerFailureTime;

	/**
	 * 忘记密码验证码有效时间,单位分钟
	 */
	public static String forgetPwdFailureTime;

	/**
	 * 登录验证码有效时间,单位分钟
	 */
	public static String loginFailureTime;

	/**
	 * 资源文件路径
	 */
	private static String qcloudProperties = "resource/qcloudSms.properties";

	public QcloudSmsConfig() {
		// 读取腾讯云短信配置文件
		try {
			Properties prop = PropertiesUtil.getProperties(qcloudProperties);
			QcloudSmsConfig.appId = Integer.parseInt(prop.getProperty("appId"));
			QcloudSmsConfig.appKey = prop.getProperty("appKey");
			QcloudSmsConfig.smsSign = prop.getProperty("smsSign");
			QcloudSmsConfig.smsCodeLength = Integer.parseInt(prop.getProperty("smsCodeLength"));
			QcloudSmsConfig.registerTemplateId = Integer.parseInt(prop.getProperty("registerTemplateId"));
			QcloudSmsConfig.forgetPwdTemplateId = Integer.parseInt(prop.getProperty("forgetPwdTemplateId"));
			QcloudSmsConfig.loginTemplateId = Integer.parseInt(prop.getProperty("loginTemplateId"));
			QcloudSmsConfig.registerFailureTime = prop.getProperty("registerFailureTime");
			QcloudSmsConfig.forgetPwdFailureTime = prop.getProperty("forgetPwdFailureTime");
			QcloudSmsConfig.loginFailureTime = prop.getProperty("loginFailureTime");
			System.out.println("短信配置-appId：" + appId);
			System.out.println("短信配置-smsCodeLength：" + smsCodeLength);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("短信配置读取错误");
		}

	}

}
