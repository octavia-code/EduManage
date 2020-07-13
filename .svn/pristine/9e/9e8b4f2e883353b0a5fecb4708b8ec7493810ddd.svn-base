package org.jit.sose.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.jit.sose.util.JWTUtil;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取请求头中的token权限<br>
 * 先在web.xml中配置拦截器
 * 
 * @author: 王越
 * @date: 2019-09-10 11:00:40
 */
@Slf4j
public class TokenRequestFilter implements Filter {

	/**
	 * 用于完成Filter的初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("TokenRequestFilter Initialize...");
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
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		// 从头中获取token
//		String token = request.getHeader("Authorization");
//		log.info("token:" + token);

		// 从cookie中获取token
		Cookie[] cookies = request.getCookies();
		String token = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				switch (cookie.getName()) {
				case "Authorization":
					token = cookie.getValue();
				default:
					break;
				}
			}
			log.info("token:" + token);
		}

		// token不存在
		if (token == null || "null".equals(token)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		// token是否有效
		boolean isExpiration = JWTUtil.isFailure(token);

		// token过期
		if (isExpiration) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		// token存在并未过期
//		if (!isExpiration) {
		// 通过TokenRequestWrapper获取请求参数
		TokenRequestWrapper tokenRequestWrapper = new TokenRequestWrapper((HttpServletRequest) servletRequest);
		Map<String, String[]> parameterMap = new HashMap<>(tokenRequestWrapper.getParameterMap());
		// 解析token
		Claims claims = JWTUtil.parseJWT(token);

		// 将获取的token放到请求参数中，依次往下传
		String[] strings = new String[1];
		strings[0] = JWTUtil.getUserId(claims).toString();
		parameterMap.put("userId", strings);
		tokenRequestWrapper.setParameterMap(parameterMap);
		filterChain.doFilter(tokenRequestWrapper, servletResponse);
//		}
	}

	/**
	 * 用于Filter销毁前，完成某些资源的回收
	 */
	@Override
	public void destroy() {
		log.info("TokenRequestFilter Destroy...");
	}

}
