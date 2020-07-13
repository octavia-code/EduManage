package org.jit.sose.util;

import org.jit.sose.exception.DataFormatException;

import com.alibaba.fastjson.JSONObject;

public class DataExceptionUtil {

	/**
	 * 检查String类型数据是否是空
	 * 
	 * @param value 需要验证的String数据
	 * @param msg   错误提示信息
	 */
	public static void stringEmpty(String value, String msg) {
		if (value == null || "".equals(value.trim())) {
			throw new DataFormatException(msg);
		}
	}

	/**
	 * 检查String类型数据长度是否超过限制
	 * 
	 * @param value  需要验证的String数据
	 * @param length String数据限制长度
	 * @param msg    错误提示信息
	 */
	public static void stringLength(String value, Integer length, String msg) {
		if (value.length() > length) {
			throw new DataFormatException(msg);
		}
	}

}
