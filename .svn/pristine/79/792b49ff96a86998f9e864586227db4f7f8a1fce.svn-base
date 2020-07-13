package org.jit.sose.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 认证失败处理器
 * 
 * @author: dylan
 * @date: 2019年5月7日 下午5:04:51
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	// 转换一个对象为json字符串
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException aException) throws IOException, ServletException {
		Map resultMap = new HashMap();
		resultMap.put("info", "fail");

		String json = objectMapper.writeValueAsString(resultMap);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
