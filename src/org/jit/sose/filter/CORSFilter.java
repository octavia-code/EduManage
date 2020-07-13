package org.jit.sose.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 先在web.xml中配置拦截器 "跨域资源共享"（Cross-origin resource sharing）<br>
 * 跨域请求设置请求头
 * 
 * @author: 王越
 * @date: 2019年5月21日 下午2:19:42
 */
@Slf4j
public class CORSFilter implements Filter {

	/**
	 * 用于完成Filter的初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("CORSFilter Initialize...");
	}

	/**
	 * 实现过滤功能，该方法就是对每个请求及响应增加的额外处理。<br>
	 * 该方法可以实现对用户请求进行预处理(ServletRequest request)，<br>
	 * 也可实现对服务器响应进行后处理(ServletResponse response)；<br>
	 * 它们的分界线为是否调用了chain.doFilter()，<br>
	 * 执行该方法之前，即对用户请求进行预处理，<br>
	 * 执行该方法之后，即对服务器响应进行后处理。<br>
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		HttpServletRequest request = (HttpServletRequest) servletRequest;

		// 较灵活的设置方式，允许所有包含 mydomain.com 的域名访问.
//		if(request.getHeader("Origin").contains("mydomain.com")) {
//		    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//		}

		// 设置允许的来源。带有cookie时，必须是具体的值
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080");

		// 允许请求的HTTP Method
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		// 授权的时间
		response.setHeader("Access-Control-Max-Age", "3600");
		// 控制哪些header能发送真正的请求。若设置为x-requested-with,content-type，只允许ajax这两种请求。
		response.setHeader("Access-Control-Allow-Headers", "*");
		// 设置为true，允许ajax异步请求带cookie信息
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// 使IE8、9实现跨域请求
		response.setHeader("XDomainRequestAllowed", "1");

//		if (request.getMethod().equals("OPTIONS")) {
//			response.setStatus(200);
//			return;
//		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * 用于Filter销毁前，完成某些资源的回收
	 */
	@Override
	public void destroy() {
		log.info("CORSFilter Destroy...");
	}

}
