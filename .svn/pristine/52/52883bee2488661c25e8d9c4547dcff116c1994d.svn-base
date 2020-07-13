package org.jit.sose.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastjsonUtil {

	/**
	 * 获取json对象中的int类型的值<br>
	 * 若值为空返回null
	 * 
	 * @param jsonObject
	 * @param key
	 * @return 获取的int值
	 */
	public static Integer getIntValue(JSONObject jsonObject, String key) {
		return StringUtil.isEmpty(jsonObject.getString(key)) ? null : jsonObject.getIntValue(key);
	}

	/**
	 * json字符串转成Integer类型的List集合
	 * 
	 * @param str      json字符串
	 * @param listName 需要获取的对象名
	 * @return Integer类型的List集合
	 */
	public static List<Integer> toIntegerList(String str, String listName) {
		// 获取jsonobject对象
		JSONObject obj = JSONObject.parseObject(str);
		// 获取的结果集合转换成数组
		JSONArray jsonArray = obj.getJSONArray(listName);
		// 将array数组转换成字符串
		String jssonString = JSONObject.toJSONString(jsonArray, SerializerFeature.WriteClassName);
		// 把字符串转换成集合
		List<Integer> list = JSON.parseArray(jssonString, Integer.class);
		return list;
	}

	/**
	 * json字符串转成String类型的List集合
	 * 
	 * @param str      json字符串
	 * @param listName 需要获取的对象名
	 * @return Integer类型的List集合
	 */
	public static List<String> toStringList(String str, String listName) {
		// 获取jsonobject对象
		JSONObject obj = JSONObject.parseObject(str);
		// 获取的结果集合转换成数组
		JSONArray jsonArray = obj.getJSONArray(listName);
		// 将array数组转换成字符串
		String jssonString = JSONObject.toJSONString(jsonArray, SerializerFeature.WriteClassName);
		// 把字符串转换成集合
		List<String> list = JSON.parseArray(jssonString, String.class);
		return list;
	}

	/**
	 * json字符串转成Double类型的List集合
	 * 
	 * @param str      json字符串
	 * @param listName 需要获取的对象名
	 * @return Integer类型的List集合
	 */
	public static List<Double> toDoubleList(String str, String listName) {
		// 获取jsonobject对象
		JSONObject obj = JSONObject.parseObject(str);
		// 获取的结果集合转换成数组
		JSONArray jsonArray = obj.getJSONArray(listName);
		// 将array数组转换成字符串
		String jssonString = JSONObject.toJSONString(jsonArray, SerializerFeature.WriteClassName);
		// 把字符串转换成集合
		List<Double> list = JSON.parseArray(jssonString, Double.class);
		return list;
	}
}
