package org.jit.sose.util;

/**
 * 自定义String工具类
 * 
 * @author: 王越
 * @date: 2019-06-06 16:20:12
 */
public class StringUtil {

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 * @return 如果为空，则返回true
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) || str.trim().length() == 0;
	}

	/**
	 * 判断字符串是否非空
	 * 
	 * @param str 如果不为空，则返回true
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 去除String空格
	 * 
	 * @param str
	 * @return 去除空格后的字符串
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 驼峰转换为下划线
	 * 
	 * @param camelCaseName 驼峰格式的字符串
	 * @return 下划线格式的字符串
	 */
	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			// 将第一个字符小写
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				// 如果有大写字符
				if (Character.isUpperCase(ch)) {
					// 先添加下划线
					result.append("_");
					// 再讲大写字符转为小写
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 将有后缀的字符转先取出后缀<br>
	 * 再将驼峰转换为下划线<br>
	 * 如果不是驼峰，在最后加一个下划线"_"
	 * 
	 * @param camelCaseName 驼峰格式的字符串
	 * @param suffix        后缀
	 * @return 下划线格式的字符串
	 */
	public static String underscoreName(String camelCaseName, String suffix) {
		// 去出字符串后缀
		camelCaseName = camelCaseName.substring(0, camelCaseName.length() - (".html".length()));
		StringBuilder result = new StringBuilder();
		// 定义是否添加下划线的flag
		boolean isAddUnderline = true;
		if (camelCaseName != null && camelCaseName.length() > 0) {
			// 将第一个字符小写
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				// 如果有大写字符
				if (Character.isUpperCase(ch)) {
					// 先添加下划线
					result.append("_");
					// 再讲大写字符转为小写
					result.append(Character.toLowerCase(ch));
					// 是驼峰，就不用加下划线
					isAddUnderline = false;
				} else {
					result.append(ch);
				}
			}
			// 根据flag判断是否加下划线
			if (isAddUnderline) {
				result.append("_");
			}
		}
		return result.toString();
	}

	/**
	 * 转换为驼峰
	 * 
	 * @param underscoreName
	 * @return
	 */
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

}
