package org.jit.sose.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 认证成功处理器
 * 
 * @author: dylan
 * @date: 2019年5月7日 下午4:37:33
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	// 转换一个对象为json字符串
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Map resultMap = new HashMap();
		resultMap.put("info", "success");
		
		String json = objectMapper.writeValueAsString(resultMap);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
