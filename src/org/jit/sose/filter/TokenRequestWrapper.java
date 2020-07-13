package org.jit.sose.filter;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 处理请求参数中的token
 * 
 * @author: dylan   
 * @date: 2019-09-09 22:06:01
 */
public class TokenRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> parameterMap; // 所有参数的Map集合

	TokenRequestWrapper(HttpServletRequest request) {
		super(request);
		parameterMap = request.getParameterMap();
	}

	// 重写几个HttpServletRequestWrapper中的方法

	/**
	 * 获取所有参数名
	 *
	 * @return 返回所有参数名
	 */
	@Override
	public Enumeration<String> getParameterNames() {
		Vector<String> vector = new Vector<String>(parameterMap.keySet());
		return vector.elements();
	}

	/**
	 * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
	 *
	 * @param name 指定参数名
	 * @return 指定参数名的值
	 */
	@Override
	public String getParameter(String name) {
		String[] results = parameterMap.get(name);
		return results[0];
	}

	/**
	 * 获取指定参数名的所有值的数组，如：checkbox的所有数据 接收数组变量 ，如checkobx类型
	 */
	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

}
