package org.jit.sose.util;

import javax.servlet.http.HttpServletRequest;

/**
 * web请求相关工具类
 * 
 * @author: dylan
 * @date: 2019-09-14 20:04:17
 */
public class WebServletUtil {

	/**
	 * 获取客户端ip地址(可以穿透代理)
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR", "X-Real-IP" };

	/***
	 * 获取客户端ip地址(可以穿透代理)
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIpAddress(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	/***
	 * 获取客户端ip地址(可以穿透代理)
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_VIA");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
			// get first ip from proxy ip
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	/**
	 * 获取reqest中的信息
	 * 
	 * @param request
	 */
	public static void getRequestInfo(HttpServletRequest request) {
//		StringTokenizer st = new StringTokenizer("agent", ";");
//		st.nextToken();
////得到用户的浏览器名
//		String userbrowser = st.nextToken();
//		System.out.println(userbrowser);
////得到用户的操作系统名
//		String useros = st.nextToken();
//		System.out.println(useros);
		// 取得本机的信息也可以这样：
		// 操作系统信息
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("os.arch"));

		System.out.println(request.getHeader("user-agent")); // 返回客户端浏览器的版本号、类型
		System.out.println(request.getMethod()); // ：获得客户端向服务器端传送数据的方法有get、post、put等类型
		System.out.println(request.getRequestURI()); // ：获得发出请求字符串的客户端地址
		System.out.println(request.getServletPath()); // ：获得客户端所请求的脚本文件的文件路径
		System.out.println(request.getServerName()); // ：获得服务器的名字
		System.out.println(request.getServerPort()); // ：获得服务器的端口号
		System.out.println(request.getRemoteAddr()); // ：获得客户端的ip地址
		System.out.println(request.getRemoteHost()); // ：获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
		System.out.println(request.getProtocol()); // ：
		System.out.println(request.getHeaderNames()); // ：返回所有request header的名字，结果集是一个enumeration（枚举）类的实例

		System.out.println("Session Id: " + request.getRequestedSessionId());
		System.out.println("Content Length: " + request.getContentLength());
		System.out.println("Remote User: " + request.getRemoteUser());
		System.out.println("Request URI: " + request.getRequestURI());
		System.out.println("Host: " + request.getHeader("Host"));
		System.out.println("Connection : " + request.getHeader("Connection"));
		System.out.println("Cookie : " + request.getHeader("Cookie"));
		System.out.println("Created : " + request.getSession().getCreationTime());

		// 获取当前请求的路径
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		System.out.println("basePath:" + basePath);

		// 获取来访者地址。只有通过链接访问当前页的时候，才能获取上一页的地址；
		String refererUrl = request.getHeader("Referer");
		// 获取全路径
		StringBuffer requestUrl = request.getRequestURL();
		// 除去host（域名或者ip）部分的路径
		String requestUri = request.getRequestURI();
		// 返回工程名部分，如果工程映射为/，此处返回则为空
		String contextPath = request.getContextPath();
		// 除去host和工程名部分的路径
		String servletPath = request.getServletPath();

		// http://127.0.0.1:8080/manage/course_info
		System.out.println("request.getHeader(\"Referer\")：" + refererUrl);
		// http://127.0.0.1:8080/manage/courseInfo/selectPageInfo.sose
		System.out.println("request.getRequestURL()：" + requestUrl);
		// /manage/courseInfo/selectPageInfo.sose
		System.out.println("request.getRequestURI()：" + requestUri);
		// 空
		System.out.println("request.getContextPath()：" + contextPath);
		// /manage/courseInfo/selectPageInfo.sose
		System.out.println("request.getServletPath()：" + servletPath);
	}

}
